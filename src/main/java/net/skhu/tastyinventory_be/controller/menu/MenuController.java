package net.skhu.tastyinventory_be.controller.menu;

import lombok.RequiredArgsConstructor;
import net.skhu.tastyinventory_be.common.dto.BaseResponse;
import net.skhu.tastyinventory_be.dto.InventoryResponseDto;
import net.skhu.tastyinventory_be.dto.InventorySaveRequestDto;
import net.skhu.tastyinventory_be.dto.MenuSaveRequestDto;
import net.skhu.tastyinventory_be.exception.SuccessCode;
import net.skhu.tastyinventory_be.service.Menu.MenuService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuController {
    //    @GetMapping("/{inventoryId}")
//    public BaseResponse<InventoryResponseDto> getInventoryById(@PathVariable("inventoryId") Long inventoryId) {
//        final InventoryResponseDto data = inventoryService.findById(inventoryId);
//        return BaseResponse.success(SuccessCode.GET_SUCCESS, data);

    private final MenuService menuService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<?> save(@RequestBody MenuSaveRequestDto requestDto) {
        menuService.save(requestDto);
        return BaseResponse.success(SuccessCode.MENU_CREATED_SUCCESS);
    }


}
