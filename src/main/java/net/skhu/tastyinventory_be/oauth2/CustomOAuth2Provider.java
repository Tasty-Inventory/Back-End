package net.skhu.tastyinventory_be.oauth2;

public enum CustomOAuth2Provider {
    GOOGLE, KAKAO, NAVER;

    public ClientRegistration.ClientRegistrationBuilder getBuilder(String registrationId) {
        return ClientRegistration.builder().registrationId(registrationId);
    }
}
