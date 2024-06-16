package net.skhu.tastyinventory_be.controller.inventoryRecord;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.skhu.tastyinventory_be.common.dto.BaseResponse;
import net.skhu.tastyinventory_be.controller.inventoryRecord.dto.request.InventoryRecordDto;
import net.skhu.tastyinventory_be.controller.inventoryRecord.dto.request.InventoryRecordRequestDto;
import net.skhu.tastyinventory_be.controller.inventoryRecord.dto.request.InventoryRecordUpdateRequestDto;
import net.skhu.tastyinventory_be.controller.inventoryRecord.dto.response.InventoryRecordResponseDto;
import net.skhu.tastyinventory_be.domain.inventoryRecord.InventoryRecord;
import net.skhu.tastyinventory_be.exception.SuccessCode;
import net.skhu.tastyinventory_be.service.InventoryRecordService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping
    public BaseResponse<List<InventoryRecordResponseDto>> getAllRecords() {
        return BaseResponse.success(SuccessCode.INVENTORY_RECORD_GET_SUCCESS, inventoryRecordService.getAllInventoryRecords());
    }
    @PatchMapping("/{id}")
    public BaseResponse<?> updateRecord(@PathVariable Long id, @RequestBody @Valid InventoryRecordUpdateRequestDto requestDto) {
        inventoryRecordService.updateInventoryRecord(id, requestDto);
        return BaseResponse.success(SuccessCode.INVENTORY_RECORD_PATCH_SUCCESS);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public BaseResponse<?> deleteRecord(@PathVariable Long id) {
        inventoryRecordService.deleteInventoryRecord(id);
        return BaseResponse.success(SuccessCode.INVENTORY_RECORD_DELETE_SUCCESS);
    }

}
