package net.skhu.tastyinventory_be.controller.sold;

import com.fasterxml.jackson.databind.ser.Serializers;
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
    public BaseResponse<List<SoldResponseDto>> findAllSold() {
        List<SoldResponseDto> soldResponseDtoList = soldService.findAllSold();
        return BaseResponse.success(SuccessCode.GET_SUCCESS, soldResponseDtoList);
    }
    @PatchMapping("/{id}")
    public BaseResponse<?> updateSold(@PathVariable("id") Long id, @RequestBody @Valid SoldRequestDto requestDto) {
        soldService.updateSold(id, requestDto);
        return BaseResponse.success(SuccessCode.SOLD_PATCH_SUCCESSCODE);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<?> deleteSold(@PathVariable("id") Long id) {
        soldService.deleteSold(id);
        return BaseResponse.success(SuccessCode.SOLD_DELETE_SUCCESSCODE);
    }
}
