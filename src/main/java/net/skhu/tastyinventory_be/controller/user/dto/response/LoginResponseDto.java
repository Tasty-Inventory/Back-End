package net.skhu.tastyinventory_be.controller.user.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginResponseDto {
    private String email;

    public static LoginResponseDto of(String email) {
        return new LoginResponseDto(email);
    }
}
