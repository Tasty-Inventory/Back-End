package net.skhu.tastyinventory_be.controller.hackathon;

import lombok.RequiredArgsConstructor;
import net.skhu.tastyinventory_be.common.dto.BaseResponse;
import net.skhu.tastyinventory_be.controller.hackathon.dto.request.FitnessRequestDto;
import net.skhu.tastyinventory_be.controller.hackathon.dto.request.FoodRequestDto;
import net.skhu.tastyinventory_be.controller.hackathon.dto.request.WeightRequestDto;
import net.skhu.tastyinventory_be.controller.hackathon.dto.response.FitnessResponseDto;
import net.skhu.tastyinventory_be.controller.hackathon.dto.response.FoodResponseDto;
import net.skhu.tastyinventory_be.controller.hackathon.dto.response.UserInfoResponseDto;
import net.skhu.tastyinventory_be.domain.user.User;
import net.skhu.tastyinventory_be.domain.user.UserRepository;
import net.skhu.tastyinventory_be.exception.ErrorCode;
import net.skhu.tastyinventory_be.exception.SuccessCode;
import net.skhu.tastyinventory_be.exception.model.NotFoundException;
import net.skhu.tastyinventory_be.security.UserDetailsImpl;
import net.skhu.tastyinventory_be.service.HackathonService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RequiredArgsConstructor
@RequestMapping
@RestController
public class HackathonController {

    private final HackathonService hackathonService;
    private final UserRepository userRepository;

    @PostMapping("/food")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<?> insertFood(
            @RequestParam String meal,
            @RequestParam String date,
            @RequestBody FoodRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl loginUser
    ) {
        String[] dateInfo = date.split("-");

        LocalDate localDate = LocalDate.of(
                Integer.parseInt(dateInfo[0]),
                Integer.parseInt(dateInfo[1]),
                Integer.parseInt(dateInfo[2])
        );

        User user = userRepository.findByUsername(loginUser.getUsername())
                        .orElseThrow(() -> new NotFoundException(
                                ErrorCode.NOT_FOUND_USER_EXCEPTION, ErrorCode.NOT_FOUND_USER_EXCEPTION.getMessage()
                        ));


        hackathonService.insertFood(requestDto, meal, localDate, user);

        return BaseResponse.success(SuccessCode.MENU_CREATE_SUCCESS);
    }

    @GetMapping("/food")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<FoodResponseDto> checkFood(
            @RequestParam String date,
            @AuthenticationPrincipal UserDetailsImpl loginUser
            ) {
        String[] dateInfo = date.split("-");

        LocalDate localDate = LocalDate.of(
                Integer.parseInt(dateInfo[0]),
                Integer.parseInt(dateInfo[1]),
                Integer.parseInt(dateInfo[2])
        );

        User user = userRepository.findByUsername(loginUser.getUsername())
                .orElseThrow(() -> new NotFoundException(
                        ErrorCode.NOT_FOUND_USER_EXCEPTION, ErrorCode.NOT_FOUND_USER_EXCEPTION.getMessage()
                ));

        final FoodResponseDto data = hackathonService.checkFood(localDate, user);
        return BaseResponse.success(SuccessCode.GET_SUCCESS, data);
    }

    @PostMapping("/weight")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<?> insertWeight(
            @RequestParam String date,
            @AuthenticationPrincipal UserDetailsImpl loginUser,
            @RequestBody WeightRequestDto requestDto
            ) {
        String[] dateInfo = date.split("-");

        LocalDate localDate = LocalDate.of(
                Integer.parseInt(dateInfo[0]),
                Integer.parseInt(dateInfo[1]),
                Integer.parseInt(dateInfo[2])
        );

        User user = userRepository.findByUsername(loginUser.getUsername())
                .orElseThrow(() -> new NotFoundException(
                        ErrorCode.NOT_FOUND_USER_EXCEPTION, ErrorCode.NOT_FOUND_USER_EXCEPTION.getMessage()
                ));

        hackathonService.insertWeight(requestDto, localDate, user);

        return BaseResponse.success(SuccessCode.SOLD_CREATE_SUCCESS);
    }

    @GetMapping("/weight")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<Integer> getWeight(
            @RequestParam String date,
            @AuthenticationPrincipal UserDetailsImpl loginUser
    ) {
        String[] dateInfo = date.split("-");

        LocalDate localDate = LocalDate.of(
                Integer.parseInt(dateInfo[0]),
                Integer.parseInt(dateInfo[1]),
                Integer.parseInt(dateInfo[2])
        );

        User user = userRepository.findByUsername(loginUser.getUsername())
                .orElseThrow(() -> new NotFoundException(
                        ErrorCode.NOT_FOUND_USER_EXCEPTION, ErrorCode.NOT_FOUND_USER_EXCEPTION.getMessage()
                ));

        final Integer data = hackathonService.getWeight(localDate, user);

        return BaseResponse.success(SuccessCode.GET_SUCCESS, data);
    }

    @PostMapping("/fitness")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<?> insertFitness(
            @RequestParam String date,
            @AuthenticationPrincipal UserDetailsImpl loginUser,
            @RequestBody FitnessRequestDto requestDto
            ) {
        String[] dateInfo = date.split("-");

        LocalDate localDate = LocalDate.of(
                Integer.parseInt(dateInfo[0]),
                Integer.parseInt(dateInfo[1]),
                Integer.parseInt(dateInfo[2])
        );

        User user = userRepository.findByUsername(loginUser.getUsername())
                .orElseThrow(() -> new NotFoundException(
                        ErrorCode.NOT_FOUND_USER_EXCEPTION, ErrorCode.NOT_FOUND_USER_EXCEPTION.getMessage()
                ));

        hackathonService.insertFitness(localDate, user, requestDto);
        return BaseResponse.success(SuccessCode.MENU_CREATE_SUCCESS);
    }

    @GetMapping("/fitness")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<FitnessResponseDto> getFitness(
            @RequestParam String date,
            @AuthenticationPrincipal UserDetailsImpl loginUser
    ) {
        String[] dateInfo = date.split("-");

        LocalDate localDate = LocalDate.of(
                Integer.parseInt(dateInfo[0]),
                Integer.parseInt(dateInfo[1]),
                Integer.parseInt(dateInfo[2])
        );

        User user = userRepository.findByUsername(loginUser.getUsername())
                .orElseThrow(() -> new NotFoundException(
                        ErrorCode.NOT_FOUND_USER_EXCEPTION, ErrorCode.NOT_FOUND_USER_EXCEPTION.getMessage()
                ));

        final FitnessResponseDto data = hackathonService.getFitness(localDate, user);
        return BaseResponse.success(SuccessCode.GET_SUCCESS, data);
    }

    @GetMapping("/user-info")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<UserInfoResponseDto> getUserInfo(
            @RequestParam String date,
            @AuthenticationPrincipal UserDetailsImpl loginUser
    ) {
        String[] dateInfo = date.split("-");

        LocalDate localDate = LocalDate.of(
                Integer.parseInt(dateInfo[0]),
                Integer.parseInt(dateInfo[1]),
                Integer.parseInt(dateInfo[2])
        );

        User user = userRepository.findByUsername(loginUser.getUsername())
                .orElseThrow(() -> new NotFoundException(
                        ErrorCode.NOT_FOUND_USER_EXCEPTION, ErrorCode.NOT_FOUND_USER_EXCEPTION.getMessage()
                ));

        final UserInfoResponseDto data = hackathonService.getUserInfo(localDate, user);
        return BaseResponse.success(SuccessCode.GET_SUCCESS, data);
    }

}
