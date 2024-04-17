package net.skhu.tastyinventory_be.controller.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorizationRequest {
    @NotBlank(message = "이메일을 입력하세요.")
    private String username;
    @NotBlank(message = "패스워드를 입력하세요.")
    private String password;
}
