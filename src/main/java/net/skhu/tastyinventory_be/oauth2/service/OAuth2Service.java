package net.skhu.tastyinventory_be.oauth2.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.skhu.tastyinventory_be.exception.ErrorCode;
import net.skhu.tastyinventory_be.exception.model.OAuth2RequestFailedException;
import net.skhu.tastyinventory_be.oauth2.ClientRegistration;
import net.skhu.tastyinventory_be.oauth2.OAuth2Token;
import net.skhu.tastyinventory_be.oauth2.userinfo.OAuth2UserInfo;
import net.skhu.tastyinventory_be.oauth2.userinfo.OAuth2UserInfoFactory;
import net.skhu.tastyinventory_be.util.JsonUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class OAuth2Service {
    protected final RestTemplate restTemplate;

    public String redirectAuthorizePage(ClientRegistration clientRegistration, String state, HttpServletResponse response) throws IOException {
        String authorizationUri = UriComponentsBuilder.fromUriString(clientRegistration.getProviderDetails().getAuthorizationUri())
                .queryParam("client_id", clientRegistration.getClientId())
                .queryParam("response_type", "code")
                .queryParam("access_type", "offline")
                .queryParam("include_granted_scopes", true)
                .queryParam("scope", String.join("+", clientRegistration.getScopes()))
                .queryParam("state", state)
                .queryParam("redirect_uri", clientRegistration.getRedirectUri())
                .build().encode(StandardCharsets.UTF_8).toUriString();
//        response.sendRedirect(authorizationUri);
        return authorizationUri;
    }

    public OAuth2Token getAccessToken(ClientRegistration clientRegistration, String code, String state) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("client_id", clientRegistration.getClientId());
        params.add("client_secret", clientRegistration.getClientSecret());
        params.add("grant_type", clientRegistration.getAuthorizationGrantType());
        params.add("code", code);
        params.add("state", state);
        params.add("redirect_uri", clientRegistration.getRedirectUri());

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(params, headers);

        ResponseEntity<String> entity = null;
        try {
            entity = restTemplate.exchange(clientRegistration.getProviderDetails().getTokenUri(), HttpMethod.POST, httpEntity, String.class);
        } catch (HttpStatusCodeException exception) {
            throw new OAuth2RequestFailedException(
                    ErrorCode.OAUTH2_TOKEN_REQUEST_FAILED,
                    ErrorCode.OAUTH2_TOKEN_REQUEST_FAILED.getMessage() +
                            String.format("[%s]", clientRegistration.getRegistrationId().toUpperCase())
            );
        }

        log.debug(entity.getBody());
        JsonObject jsonObject = JsonUtils.parse(entity.getBody()).getAsJsonObject();
        String accessToken = jsonObject.get("access_token").getAsString();
        Optional<JsonElement> optionalRefreshToken = Optional.ofNullable(jsonObject.get("refresh_token"));
        LocalDateTime expiredAt = LocalDateTime.now().plusSeconds(jsonObject.get("expires_in").getAsLong());

        return new OAuth2Token(accessToken, optionalRefreshToken.map(JsonElement::getAsString).orElse(null), expiredAt);
    }

    protected OAuth2Token refreshOAuth2Token(ClientRegistration clientRegistration, OAuth2Token token) {
        if (LocalDateTime.now().isBefore(token.getExpiredAt())) return token;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("client_id", clientRegistration.getClientId());
        params.add("client_secret", clientRegistration.getClientSecret());
        params.add("grant_type", "refresh_token");
        params.add("refresh_token", token.getRefreshToken());

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(params, headers);

        ResponseEntity<String> entity = null;
        try {
            entity = restTemplate.exchange(clientRegistration.getProviderDetails().getTokenUri(), HttpMethod.POST, httpEntity, String.class);
        } catch (HttpStatusCodeException exception) {
            int statusCode = exception.getStatusCode().value();
            throw new OAuth2RequestFailedException(ErrorCode.OAUTH2_REFRESH_TOKEN_FAILED, ErrorCode.OAUTH2_REFRESH_TOKEN_FAILED.getMessage());
        }

        JsonObject jsonObject = JsonUtils.parse(entity.getBody()).getAsJsonObject();
        String accessToken = jsonObject.get("access_token").getAsString();
        Optional<JsonElement> optionalNewRefreshToken = Optional.ofNullable(jsonObject.get("refresh_token"));
        LocalDateTime expiredAt = LocalDateTime.now().plusSeconds(jsonObject.get("expires_in").getAsLong());

        return new OAuth2Token(accessToken, optionalNewRefreshToken.isPresent() ? optionalNewRefreshToken.get().getAsString() : token.getRefreshToken(), expiredAt);
    }

    public OAuth2UserInfo getUserInfo(ClientRegistration clientRegistration, String accessToken) {
        HttpHeaders headers = new HttpHeaders();

        headers.add("Authorization", "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> entity = null;
        try {
            entity = restTemplate.exchange(clientRegistration.getProviderDetails().getUserInfoUri(), HttpMethod.GET, httpEntity, String.class);
        } catch (HttpStatusCodeException exception) {
            throw new OAuth2RequestFailedException(
                    ErrorCode.OAUTH2_USERINFO_REQUEST_FAILED,
                    ErrorCode.OAUTH2_USERINFO_REQUEST_FAILED.getMessage() + String.format("[%s]", clientRegistration.getRegistrationId().toUpperCase())
            );
        }

        Map<String, Object> userAttributes = JsonUtils.fromJson(entity.getBody(), Map.class);

        return OAuth2UserInfoFactory.getOAuth2UserInfo(clientRegistration.getRegistrationId(), userAttributes);
    }


    public abstract void unlink(ClientRegistration clientRegistration, OAuth2Token token);
}
