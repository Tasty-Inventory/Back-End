package net.skhu.tastyinventory_be.controller.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.skhu.tastyinventory_be.common.dto.BaseResponse;
import net.skhu.tastyinventory_be.controller.user.dto.SignUpRequestDto;
import net.skhu.tastyinventory_be.exception.SuccessCode;
import net.skhu.tastyinventory_be.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {
    private final UserService userService;

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse signUpNewUser(@RequestBody @Valid SignUpRequestDto signUpRequestDto) {
        userService.saveUser(signUpRequestDto);
        return BaseResponse.success(SuccessCode.SIGNUP_SUCCESS);
    }
}
