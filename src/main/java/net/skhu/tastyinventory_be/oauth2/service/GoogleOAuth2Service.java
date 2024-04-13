package net.skhu.tastyinventory_be.oauth2.service;

import lombok.extern.slf4j.Slf4j;
import net.skhu.tastyinventory_be.exception.ErrorCode;
import net.skhu.tastyinventory_be.exception.model.OAuth2RequestFailedException;
import net.skhu.tastyinventory_be.oauth2.ClientRegistration;
import net.skhu.tastyinventory_be.oauth2.OAuth2Token;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
public class GoogleOAuth2Service extends OAuth2Service {
    public GoogleOAuth2Service(RestTemplate restTemplate) {
        super(restTemplate);
    }

    @Override
    public void unlink(ClientRegistration clientRegistration, OAuth2Token token) {
        token = refreshOAuth2Token(clientRegistration, token);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(headers);

        String unlinkUri = UriComponentsBuilder.fromUriString(clientRegistration.getProviderDetails().getUnlinkUri())
                .queryParam("token", token.getToken()).encode().build().toUriString();

        ResponseEntity<String> entity = null;
        try {
            entity = restTemplate.exchange(unlinkUri, HttpMethod.GET, httpEntity, String.class);
            log.info("구글 연동해제 성공");
        } catch (HttpStatusCodeException exception) {
            int statusCode = exception.getStatusCode().value();
            throw new OAuth2RequestFailedException(
                    ErrorCode.REQUEST_VALIDATION_EXCEPTION,
                    String.format("%s 연동 해제 실패 [응답코드 : %d].", clientRegistration.getRegistrationId().toUpperCase(), statusCode)
            );
        }
    }
}
