package net.skhu.tastyinventory_be.controller.inventoryRecord;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.skhu.tastyinventory_be.common.dto.BaseResponse;
import net.skhu.tastyinventory_be.controller.inventoryRecord.dto.request.InventoryRecordRequestDto;
import net.skhu.tastyinventory_be.exception.SuccessCode;
import net.skhu.tastyinventory_be.service.InventoryRecordService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/inventory-records")
@RestController
public class InventoryRecordController {
    private final InventoryRecordService inventoryRecordService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<?> createRecord(@RequestBody @Valid InventoryRecordRequestDto requestDto) {
        inventoryRecordService.createInventoryRecord(requestDto);
        return BaseResponse.success(SuccessCode.INVENTORY_RECORD_CREATE_SUCCESS);
    }
}
