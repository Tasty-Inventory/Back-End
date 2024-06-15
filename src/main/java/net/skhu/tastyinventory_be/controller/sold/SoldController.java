package net.skhu.tastyinventory_be.controller.sold;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.skhu.tastyinventory_be.common.dto.BaseResponse;
import net.skhu.tastyinventory_be.controller.sold.dto.request.SoldRequestDto;
import net.skhu.tastyinventory_be.controller.sold.dto.response.SoldResponseDto;
import net.skhu.tastyinventory_be.exception.SuccessCode;
import net.skhu.tastyinventory_be.service.SoldService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/sold")
@RestController
public class SoldController {
    private final SoldService soldService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<?> registerSold(@RequestBody @Valid SoldRequestDto requestDto) {
        soldService.registerSold(requestDto);
        return BaseResponse.success(SuccessCode.SOLD_CREATE_SUCCESS);
    }
    @GetMapping
    public BaseResponse<List<SoldResponseDto>> getSoldAll() {
        List<SoldResponseDto> soldResponseDtoList = soldService.getSoldAll();
        return BaseResponse.success(SuccessCode.GET_SUCCESS, soldResponseDtoList);
    }
}
