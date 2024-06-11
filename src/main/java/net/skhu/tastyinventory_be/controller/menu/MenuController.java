package net.skhu.tastyinventory_be.controller.menu;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.skhu.tastyinventory_be.common.dto.BaseResponse;
import net.skhu.tastyinventory_be.controller.menu.dto.request.MenuRequestDto;
import net.skhu.tastyinventory_be.controller.menu.dto.response.MenuDetailResponseDto;
import net.skhu.tastyinventory_be.controller.menu.dto.response.MenuResponseDto;
import net.skhu.tastyinventory_be.exception.SuccessCode;
import net.skhu.tastyinventory_be.service.MenuService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/menu")
@RestController
public class MenuController {
    private final MenuService menuService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<?> createMenu(
            @RequestPart("image") MultipartFile image,
            @RequestPart("data") MenuRequestDto requestDto
    ) {
        menuService.createMenu(image, requestDto);
        return BaseResponse.success(SuccessCode.MENU_CREATE_SUCCESS);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<List<MenuResponseDto>> findAll() {
        final List<MenuResponseDto> data = menuService.findAllMenu();
        return BaseResponse.success(SuccessCode.GET_SUCCESS, data);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<MenuDetailResponseDto> findOne(@PathVariable Long id) {
        final MenuDetailResponseDto data = menuService.findOneMenu(id);
        return BaseResponse.success(SuccessCode.GET_SUCCESS, data);
    }

    @PatchMapping(
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            path = "/{id}"
    )
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<?> updateMenu(
            @RequestPart("image") MultipartFile image,
            @RequestPart("data") MenuRequestDto requestDto,
            @PathVariable Long id
    ) {
        menuService.updateMenu(id, image, requestDto);
        return BaseResponse.success(SuccessCode.MENU_PATCH_SUCCESS);
    }
}
