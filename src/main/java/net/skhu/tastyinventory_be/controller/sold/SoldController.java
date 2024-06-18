package net.skhu.tastyinventory_be.controller.sold;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.skhu.tastyinventory_be.common.dto.BaseResponse;
import net.skhu.tastyinventory_be.controller.sold.dto.request.SoldRequestDto;
import net.skhu.tastyinventory_be.controller.sold.dto.response.SoldResponseDto;
import net.skhu.tastyinventory_be.exception.SuccessCode;
import net.skhu.tastyinventory_be.service.SoldService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/sold")
@RestController
public class SoldController {
    private final SoldService soldService;

    @GetMapping
    public BaseResponse<List<SoldResponseDto>> findAllSold() {
        List<SoldResponseDto> soldResponseDtoList = soldService.findAllSold();
        return BaseResponse.success(SuccessCode.GET_SUCCESS, soldResponseDtoList);
    }

    @PutMapping
    public BaseResponse<?> updateAllSold(@RequestBody @Valid SoldRequestDto requestDto) {
        soldService.updateAllSold(requestDto);
        return BaseResponse.success(SuccessCode.SOLD_PATCH_SUCCESSCODE);
    }
}
