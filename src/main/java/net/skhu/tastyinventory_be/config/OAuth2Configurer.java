package net.skhu.tastyinventory_be.config;

import lombok.RequiredArgsConstructor;
import net.skhu.tastyinventory_be.oauth2.ClientRegistration;
import net.skhu.tastyinventory_be.oauth2.ClientRegistrationRepository;
import net.skhu.tastyinventory_be.oauth2.CustomOAuth2Provider;
import net.skhu.tastyinventory_be.oauth2.OAuth2ClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class OAuth2Configurer {
    private final OAuth2ClientProperties oAuth2ClientProperties;

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        List<ClientRegistration> registrations = oAuth2ClientProperties.getRegistration().keySet().stream()
                .map(this::getRegistration)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return new ClientRegistrationRepository(registrations);
    }

    private ClientRegistration getRegistration(String client) {
        if (client.equals("google")) {
            return CustomOAuth2Provider.GOOGLE.getBuilder(client)
                    .clientId(oAuth2ClientProperties.getRegistration().get(client).getClientId())
                    .clientSecret(oAuth2ClientProperties.getRegistration().get(client).getClientSecret())
                    .authorizationGrantType(oAuth2ClientProperties.getRegistration().get(client).getAuthorizationGrantType())
                    .redirectUri(oAuth2ClientProperties.getRegistration().get(client).getRedirectUri())
                    .scopes(oAuth2ClientProperties.getRegistration().get(client).getScope())
                    .authorizationUri(oAuth2ClientProperties.getProvider().get(client).getAuthorizationUri())
                    .tokenUri(oAuth2ClientProperties.getProvider().get(client).getTokenUri())
                    .userInfoUri(oAuth2ClientProperties.getProvider().get(client).getUserInfoUri())
                    .unlinkUri(oAuth2ClientProperties.getProvider().get(client).getUnlinkUri())
                    .build();
        }
        if (client.equals("naver")) {
            return CustomOAuth2Provider.NAVER.getBuilder(client)
                    .clientId(oAuth2ClientProperties.getRegistration().get(client).getClientId())
                    .clientSecret(oAuth2ClientProperties.getRegistration().get(client).getClientSecret())
                    .authorizationGrantType(oAuth2ClientProperties.getRegistration().get(client).getAuthorizationGrantType())
                    .redirectUri(oAuth2ClientProperties.getRegistration().get(client).getRedirectUri())
                    .scopes(oAuth2ClientProperties.getRegistration().get(client).getScope())
                    .authorizationUri(oAuth2ClientProperties.getProvider().get(client).getAuthorizationUri())
                    .tokenUri(oAuth2ClientProperties.getProvider().get(client).getTokenUri())
                    .userInfoUri(oAuth2ClientProperties.getProvider().get(client).getUserInfoUri())
                    .unlinkUri(oAuth2ClientProperties.getProvider().get(client).getUnlinkUri())
                    .build();
        }
        if (client.equals("kakao")) {
            return CustomOAuth2Provider.KAKAO.getBuilder(client)
                    .clientId(oAuth2ClientProperties.getRegistration().get(client).getClientId())
                    .clientSecret(oAuth2ClientProperties.getRegistration().get(client).getClientSecret())
                    .authorizationGrantType(oAuth2ClientProperties.getRegistration().get(client).getAuthorizationGrantType())
                    .redirectUri(oAuth2ClientProperties.getRegistration().get(client).getRedirectUri())
                    .scopes(oAuth2ClientProperties.getRegistration().get(client).getScope())
                    .authorizationUri(oAuth2ClientProperties.getProvider().get(client).getAuthorizationUri())
                    .tokenUri(oAuth2ClientProperties.getProvider().get(client).getTokenUri())
                    .userInfoUri(oAuth2ClientProperties.getProvider().get(client).getUserInfoUri())
                    .unlinkUri(oAuth2ClientProperties.getProvider().get(client).getUnlinkUri())
                    .build();
        }
        return null;
    }
}
