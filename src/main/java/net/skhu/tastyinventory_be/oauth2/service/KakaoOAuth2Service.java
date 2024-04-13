package net.skhu.tastyinventory_be.oauth2.service;

import net.skhu.tastyinventory_be.exception.ErrorCode;
import net.skhu.tastyinventory_be.exception.model.OAuth2RequestFailedException;
import net.skhu.tastyinventory_be.oauth2.ClientRegistration;
import net.skhu.tastyinventory_be.oauth2.OAuth2Token;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

public class KakaoOAuth2Service extends OAuth2Service {
    public KakaoOAuth2Service(RestTemplate restTemplate) {
        super(restTemplate);
    }

    @Override
    public void unlink(ClientRegistration clientRegistration, OAuth2Token token) {
        token = refreshOAuth2Token(clientRegistration, token);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token.getToken());

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<String> entity = null;
        try {
            entity = restTemplate.exchange(clientRegistration.getProviderDetails().getUnlinkUri(), HttpMethod.POST, httpEntity, String.class);
        } catch (HttpStatusCodeException exception) {
            int statusCode = exception.getStatusCode().value();
            throw new OAuth2RequestFailedException(
                    ErrorCode.REQUEST_VALIDATION_EXCEPTION,
                    String.format(
                            "%s 연동 해제 실패 [응답코드 : %d].",
                            clientRegistration.getRegistrationId().toUpperCase(), statusCode
                    )
            );
        }
    }
}
