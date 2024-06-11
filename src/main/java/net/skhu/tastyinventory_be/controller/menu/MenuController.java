package net.skhu.tastyinventory_be.controller.menu;

import lombok.RequiredArgsConstructor;
import net.skhu.tastyinventory_be.common.dto.BaseResponse;
import net.skhu.tastyinventory_be.domain.menu.Menu;
import net.skhu.tastyinventory_be.controller.menu.dto.MenuListResponseDto;
import net.skhu.tastyinventory_be.controller.menu.dto.MenuResponseDto;
import net.skhu.tastyinventory_be.controller.menu.dto.MenuSaveRequestDto;
import net.skhu.tastyinventory_be.controller.menu.dto.MenuUpdateRequestDto;
import net.skhu.tastyinventory_be.exception.SuccessCode;
import net.skhu.tastyinventory_be.service.Menu.MenuService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<String> save(@RequestBody MenuSaveRequestDto requestDto) {
        menuService.saveMenu(requestDto);
        return BaseResponse.success(SuccessCode.MENU_CREATED_SUCCESS);
    }
    @GetMapping("/{menuId}")
    public BaseResponse<MenuResponseDto> getmenuById(@PathVariable("menuId") Long menuId) {
        MenuResponseDto data = menuService.findById(menuId);
        return BaseResponse.success(SuccessCode.GET_SUCCESS, data);

    }

    @GetMapping
    public BaseResponse<MenuListResponseDto> getAllMenu(){
        MenuListResponseDto data = menuService.findAll();
        return BaseResponse.success(SuccessCode.GET_SUCCESS, data);
    }


    @GetMapping("/search")
    public BaseResponse<List<MenuResponseDto>> search(@RequestParam(value = "srchText", required = false) String srchText) {
        List<MenuResponseDto> responseDtoList;
        if (srchText != null && !srchText.isEmpty()) {
            List<Menu> data = menuService.searchByName(srchText);
            responseDtoList = data.stream()
                    .map(MenuResponseDto::from)
                    .collect(Collectors.toList());
        } else {
            MenuListResponseDto listResponseDto = menuService.findAll();
            responseDtoList = listResponseDto.getMenus();
        }
        return BaseResponse.success(SuccessCode.GET_SUCCESS, responseDtoList);
    }
    @PatchMapping("/{menuId}")
    public BaseResponse<String> updateMenu(@PathVariable("menuId") Long menuId, @RequestBody MenuUpdateRequestDto requestDto) {
        menuService.update(menuId, requestDto);
        return BaseResponse.success(SuccessCode.UPDATE_SUCCESS);
    }

    @DeleteMapping("/{menuId}")
    public BaseResponse<?> deleteMenu(@PathVariable("menuId") Long menuId) {
        menuService.delete(menuId);
        return BaseResponse.success(SuccessCode.DELETE_SUCCESS);
    }

}
