package net.skhu.tastyinventory_be.controller.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.skhu.tastyinventory_be.common.dto.BaseResponse;
import net.skhu.tastyinventory_be.controller.user.dto.AuthorizationRequest;
import net.skhu.tastyinventory_be.domain.user.UserRepository;
import net.skhu.tastyinventory_be.exception.SuccessCode;
import net.skhu.tastyinventory_be.security.StatelessCSRFFilter;
import net.skhu.tastyinventory_be.security.jwt.JwtProvider;
import net.skhu.tastyinventory_be.service.UserService;
import net.skhu.tastyinventory_be.util.CookieUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
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
    public BaseResponse authenticationUsernamePassword(@Valid @RequestBody AuthorizationRequest authorizationRequest, HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authorizationRequest.getUsername(), authorizationRequest.getPassword()));
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        generateTokenCookie(userDetails, request, response);
        return BaseResponse.success(SuccessCode.LOGIN_SUCCESS);
    }

    @PostMapping("/logout")
    public BaseResponse expiredToken(HttpServletRequest request, HttpServletResponse response) {
        CookieUtils.deleteCookie(request, response, "access_token");
        CookieUtils.deleteCookie(request, response, StatelessCSRFFilter.CSRF_TOKEN);
        return BaseResponse.success(SuccessCode.LOGOUT_SUCCESS);
    }

    private void generateCSRFTokenCookie(HttpServletResponse response, String csrfToken) {
        CookieUtils.addCookie(response, StatelessCSRFFilter.CSRF_TOKEN, csrfToken, 60 * 60 * 24);
    }

    private void generateTokenCookie(UserDetails userDetails, HttpServletRequest request, HttpServletResponse response) {
        final int cookieMaxAge = jwtProvider.getTokenExpirationDate().intValue();
        CookieUtils.addCookie(response, "access_token", jwtProvider.generateToken(userDetails.getUsername()), true, true, cookieMaxAge);
    }
}
