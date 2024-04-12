package net.skhu.tastyinventory_be.oauth2.userinfo;

import net.skhu.tastyinventory_be.exception.ErrorCode;
import net.skhu.tastyinventory_be.exception.model.OAuth2NotSupportException;

import java.util.Map;

public class OAuth2UserInfoFactory {
    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if (registrationId.equalsIgnoreCase("google")) {
            return new GoogleOAuth2UserInfo(attributes);
        } else if (registrationId.equalsIgnoreCase("kakao")) {
            return new KakaoOAuth2UserInfo(attributes);
        } else if (registrationId.equalsIgnoreCase("naver")) {
            return new NaverOAuth2UserInfo(attributes);
        } else {
            throw new OAuth2NotSupportException(ErrorCode.OAUTH2_NOT_SUPPORT_EXCEPTION, ErrorCode.OAUTH2_NOT_SUPPORT_EXCEPTION.getMessage());
        }
    }
}
