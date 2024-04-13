package net.skhu.tastyinventory_be.oauth2;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class OAuth2AuthorizationResponseDto {
    private String state;
    private String code;
    private String error;
}
