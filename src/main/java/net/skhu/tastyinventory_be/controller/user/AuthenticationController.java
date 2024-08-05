package net.skhu.tastyinventory_be.controller.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.skhu.tastyinventory_be.common.dto.BaseResponse;
import net.skhu.tastyinventory_be.controller.user.dto.request.AuthorizationRequest;
import net.skhu.tastyinventory_be.controller.user.dto.response.LoginResponseDto;
import net.skhu.tastyinventory_be.exception.ErrorCode;
import net.skhu.tastyinventory_be.exception.SuccessCode;
import net.skhu.tastyinventory_be.exception.model.OAuth2RequestFailedException;
import net.skhu.tastyinventory_be.oauth2.*;
import net.skhu.tastyinventory_be.oauth2.domain.OAuth2AccountDto;
import net.skhu.tastyinventory_be.oauth2.service.OAuth2Service;
import net.skhu.tastyinventory_be.oauth2.service.OAuth2ServiceFactory;
import net.skhu.tastyinventory_be.oauth2.userinfo.OAuth2UserInfo;
import net.skhu.tastyinventory_be.security.StatelessCSRFFilter;
import net.skhu.tastyinventory_be.security.UserDetailsImpl;
import net.skhu.tastyinventory_be.security.jwt.JwtProvider;
import net.skhu.tastyinventory_be.service.UserService;
import net.skhu.tastyinventory_be.util.CookieUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final ClientRegistrationRepository clientRegistrationRepository;
    private final InMemoryOAuth2RequestRepository inMemoryOAuth2RequestRepository;
    private final InMemoryOAuth2UserDetailsRepository inMemoryOAuth2UserDetailsRepository;
    private final RestTemplate restTemplate;
    private final JwtProvider jwtProvider;

    @GetMapping("/csrf-token")
    public ResponseEntity<?> getCsrfToken(HttpServletRequest request, HttpServletResponse response) {
        String csrfToken = UUID.randomUUID().toString();
        Map<String, String> resMap = new HashMap<>();
        resMap.put(StatelessCSRFFilter.CSRF_TOKEN, csrfToken);
        generateCSRFTokenCookie(response, csrfToken);
        return ResponseEntity.ok(resMap);
    }

    @PostMapping("/authorize")
    public BaseResponse<String> authenticationUsernamePassword(@Valid @RequestBody AuthorizationRequest authorizationRequest, HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authorizationRequest.getUsername(), authorizationRequest.getPassword()));
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String token = generateTokenCookie(userDetails, request, response);
        final LoginResponseDto data = LoginResponseDto.of(userDetails.getEmail());
        return BaseResponse.success(SuccessCode.LOGIN_SUCCESS, token);
    }

    @PostMapping("/logout")
    public BaseResponse expiredToken(HttpServletRequest request, HttpServletResponse response) {
        CookieUtils.deleteCookie(request, response, "access_token");
        CookieUtils.deleteCookie(request, response, StatelessCSRFFilter.CSRF_TOKEN);
        return BaseResponse.success(SuccessCode.LOGOUT_SUCCESS);
    }

    @GetMapping("/oauth2/authorize")
    public BaseResponse<LoginResponseDto> authenticationSocial(
            @RequestParam(name = "user_state") String state,
            HttpServletRequest request, HttpServletResponse response
    ) {
        UserDetailsImpl userDetails = (UserDetailsImpl) inMemoryOAuth2UserDetailsRepository.deleteUserDetails(state);
        generateTokenCookie(userDetails, request, response);
        final LoginResponseDto data = LoginResponseDto.of(userDetails.getEmail());
        return BaseResponse.success(SuccessCode.LOGIN_SUCCESS, data);
    }

    @GetMapping("/oauth2/authorize/{provider}")
    public String redirectSocialAuthorizationPage(
            @PathVariable String provider,
            @RequestParam(name = "redirect_uri") String redirectUri,
            @RequestParam String callback,
            HttpServletRequest request, HttpServletResponse response
    ) throws Exception {
        String state = generateState();

        inMemoryOAuth2RequestRepository.saveOAuth2Request(
                state,
                OAuth2AuthorizationRequestDto.builder()
                        .referer(request.getHeader("referer"))
                        .redirectUri(redirectUri)
                        .callback(callback)
                        .build()
        );

        ClientRegistration clientRegistration = clientRegistrationRepository.findByRegistrationId(provider);
        OAuth2Service oAuth2Service = OAuth2ServiceFactory.getOAuth2Service(restTemplate, provider);
        return oAuth2Service.redirectAuthorizePage(clientRegistration, state, response);
    }

    @RequestMapping("/oauth2/callback/{provider}")
    public void oAuth2AuthenticationCallback(
            @PathVariable String provider,
            OAuth2AuthorizationResponseDto oAuth2AuthorizationResponseDto,
            HttpServletRequest request,
            HttpServletResponse response,
            @AuthenticationPrincipal UserDetailsImpl loginUser
            ) throws Exception {
        OAuth2AuthorizationRequestDto oAuth2AuthorizationRequestDto = inMemoryOAuth2RequestRepository.deleteOAuth2Request(oAuth2AuthorizationResponseDto.getState());

        if (oAuth2AuthorizationResponseDto.getError() != null) {
            throw new OAuth2RequestFailedException(
                    ErrorCode.OAUTH2_REDIRECT_CALLBACK_EXCEPTION,
                    ErrorCode.OAUTH2_REDIRECT_CALLBACK_EXCEPTION.getMessage() + String.format("(%s [응답 코드: %s])", oAuth2AuthorizationResponseDto.getError(), oAuth2AuthorizationResponseDto.getCode())
            );
        }

        ClientRegistration clientRegistration = clientRegistrationRepository.findByRegistrationId(provider);
        OAuth2Service oAuth2Service = OAuth2ServiceFactory.getOAuth2Service(restTemplate, provider);

        OAuth2Token oAuth2Token = oAuth2Service.getAccessToken(clientRegistration, oAuth2AuthorizationResponseDto.getCode(), oAuth2AuthorizationResponseDto.getState());
        OAuth2UserInfo oAuth2UserInfo = oAuth2Service.getUserInfo(clientRegistration, oAuth2Token.getToken());

        if (oAuth2AuthorizationRequestDto.getCallback().equalsIgnoreCase("login")) {
            String state = generateState();
            UserDetailsImpl userDetails = (UserDetailsImpl) userService.loginOAuth2User(provider, oAuth2Token, oAuth2UserInfo);
            inMemoryOAuth2UserDetailsRepository.saveUserDetails(state, userDetails);
            String redirectUri = UriComponentsBuilder.fromUriString(oAuth2AuthorizationRequestDto.getRedirectUri())
                    .queryParam("provider", provider)
                    .queryParam("name", userDetails.getUsername())
                    .queryParam("user_state", state)
                    .build().encode(StandardCharsets.UTF_8).toUriString();
            response.sendRedirect(redirectUri);
        } else if (oAuth2AuthorizationRequestDto.getCallback().equalsIgnoreCase("link")) {
            if (loginUser == null) {
                redirectWithErrorMessage(oAuth2AuthorizationRequestDto.getReferer(), "unauthorized", response);
            }
            try {
                assert loginUser != null;
                userService.linkOAuth2Account(loginUser.getUsername(), provider, oAuth2Token, oAuth2UserInfo);
            } catch (Exception e) {
                redirectWithErrorMessage(oAuth2AuthorizationRequestDto.getReferer(), e.getMessage(), response);
            }
        }
    }

    @GetMapping("/oauth2/unlink")
    public void unlinkOAuth2Account(@AuthenticationPrincipal UserDetailsImpl loginUser) {
        OAuth2AccountDto oAuth2AccountDto = userService.unlinkOAuth2Account(loginUser.getUsername());

        ClientRegistration clientRegistration = clientRegistrationRepository.findByRegistrationId(oAuth2AccountDto.getProvider());
        OAuth2Service oAuth2Service = OAuth2ServiceFactory.getOAuth2Service(restTemplate, oAuth2AccountDto.getProvider());
        oAuth2Service.unlink(clientRegistration, oAuth2AccountDto.getOAuth2Token());
    }

    private void generateCSRFTokenCookie(HttpServletResponse response, String csrfToken) {
        CookieUtils.addCookie(response, StatelessCSRFFilter.CSRF_TOKEN, csrfToken, 60 * 60 * 24);
    }

    private String generateTokenCookie(UserDetails userDetails, HttpServletRequest request, HttpServletResponse response) {
        final int cookieMaxAge = jwtProvider.getTokenExpirationDate().intValue();
        CookieUtils.addCookie(
                response,
                "access_token",
                jwtProvider.generateToken(userDetails.getUsername()),
                true,
                true,
                cookieMaxAge
        );
        return jwtProvider.generateToken(userDetails.getUsername());
    }

    private void redirectWithErrorMessage(String uri, String message, HttpServletResponse response) throws IOException {
        String redirectUri = UriComponentsBuilder.fromUriString(uri)
                .replaceQueryParam("error", message).encode().build().toUriString();
        response.sendRedirect(redirectUri);
    }

    private String generateState() {
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32);
    }
}
