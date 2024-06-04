package net.skhu.tastyinventory_be.controller.inventory;

import lombok.RequiredArgsConstructor;

import net.skhu.tastyinventory_be.common.dto.BaseResponse;
import net.skhu.tastyinventory_be.domain.inventory.Inventory;
import net.skhu.tastyinventory_be.dto.inventory.InventoryListResponseDto;
import net.skhu.tastyinventory_be.dto.inventory.InventoryResponseDto;
import net.skhu.tastyinventory_be.dto.inventory.InventorySaveRequestDto;
import net.skhu.tastyinventory_be.dto.inventory.InventoryUpdateRequestDto;
import net.skhu.tastyinventory_be.exception.SuccessCode;
import net.skhu.tastyinventory_be.service.Inventory.InventoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("/inventory")
@RestController
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping
    public BaseResponse<Void> save(@RequestBody InventorySaveRequestDto requestDto){
        inventoryService.save(requestDto);
        return BaseResponse.success(SuccessCode.INVENTORY_CREATED_SUCCESS);
    }
    @GetMapping
    public BaseResponse<InventoryListResponseDto> getAllInventory(){
        final InventoryListResponseDto data = inventoryService.findAll();
        return BaseResponse.success(SuccessCode.GET_SUCCESS, data);
    }

    @GetMapping("/{inventoryId}")
    public BaseResponse<InventoryResponseDto> getInventoryById(@PathVariable("inventoryId") Long inventoryId) {
        final InventoryResponseDto data = inventoryService.findById(inventoryId);
        return BaseResponse.success(SuccessCode.GET_SUCCESS, data);
    }

    @GetMapping("/search")
    public BaseResponse<List<InventoryResponseDto>> search(@RequestParam(value = "srchText", required = false) String srchText) {
        List<InventoryResponseDto> responseDtoList;
        if (srchText != null && !srchText.isEmpty()) {
            List<Inventory> data = inventoryService.searchByName(srchText);
            responseDtoList = data.stream()
                    .map(InventoryResponseDto::from)
                    .collect(Collectors.toList());
        } else {
            InventoryListResponseDto listResponseDto = inventoryService.findAll();
            responseDtoList = listResponseDto.getInventories(); // 가정: InventoryListResponseDto에 getInventories() 메소드가 있다고 가정
        }
        return BaseResponse.success(SuccessCode.GET_SUCCESS, responseDtoList);
    }
    @PatchMapping("/{inventoryId}")
    public BaseResponse<?> updateInventory(@PathVariable("inventoryId") Long inventoryId, @RequestBody InventoryUpdateRequestDto requestDto) {
        inventoryService.update(inventoryId, requestDto);
        return BaseResponse.success(SuccessCode.UPDATE_SUCCESS);
    }

    @DeleteMapping("/{inventoryId}")
    public BaseResponse<?> deleteInventory(@PathVariable("inventoryId") Long inventoryId) {
        inventoryService.delete(inventoryId);
        return BaseResponse.success(SuccessCode.DELETE_SUCCESS);
    }

}
