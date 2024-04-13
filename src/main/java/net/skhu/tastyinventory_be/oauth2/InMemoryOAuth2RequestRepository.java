package net.skhu.tastyinventory_be.oauth2;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class InMemoryOAuth2RequestRepository {
    private final Map<String, OAuth2AuthorizationRequestDto> oAuth2RequestDtoMap = new HashMap<>();

    public void saveOAuth2Request(String state, OAuth2AuthorizationRequestDto oAuth2AuthorizationRequestDto) {
        oAuth2RequestDtoMap.put(state, oAuth2AuthorizationRequestDto);
    }

    public OAuth2AuthorizationRequestDto getOAuth2Request(String state) {
        return oAuth2RequestDtoMap.get(state);
    }

    public OAuth2AuthorizationRequestDto deleteOAuth2Request(String state) {
        return oAuth2RequestDtoMap.remove(state);
    }
}
