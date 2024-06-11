package net.skhu.tastyinventory_be.controller.inventory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.skhu.tastyinventory_be.common.dto.BaseResponse;
import net.skhu.tastyinventory_be.controller.inventory.dto.response.InventoryResponseDto;
import net.skhu.tastyinventory_be.domain.inventory.Unit;
import net.skhu.tastyinventory_be.exception.SuccessCode;
import net.skhu.tastyinventory_be.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/inventory")
@RestController
public class InventoryController {
    private final InventoryService inventoryService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<?> createInventory(
            @RequestParam("inventoryName") String name,
            @RequestParam("inventoryUnit") Unit unit,
            @RequestPart("inventoryImage") MultipartFile image
    ) {
        inventoryService.createInventory(name, unit, image);
        return BaseResponse.success(SuccessCode.INVENTORY_CREATE_SUCCESS);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<InventoryResponseDto> findInventory(@PathVariable Long id) {
        final InventoryResponseDto data = inventoryService.findInventory(id);
        return BaseResponse.success(SuccessCode.INVENTORY_GET_SUCCESS, data);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<List<InventoryResponseDto>> findAllByNameContaining(
            @RequestParam(name = "srchText", required = false) Optional<String> srchText
    ) {
        final List<InventoryResponseDto> data = inventoryService.findAllByNameContaining(srchText.orElse(""));
        return BaseResponse.success(SuccessCode.INVENTORY_GET_SUCCESS, data);
    }

    @PatchMapping(
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<?> updateInventory(
            @RequestParam("inventoryName") String name,
            @RequestParam("inventoryUnit") Unit unit,
            @RequestPart("inventoryImage") MultipartFile image,
            @PathVariable Long id) {
        inventoryService.updateInventory(id, name, unit, image);
        return BaseResponse.success(SuccessCode.INVENTORY_PATCH_SUCCESS);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<?> deleteInventory(@PathVariable Long id) {
        inventoryService.deleteInventory(id);
        return BaseResponse.success(SuccessCode.INVENTORY_DELETE_SUCCESS);
    }
}
