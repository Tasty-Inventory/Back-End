package net.skhu.tastyinventory_be.controller.menu.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class MenuListResponseDto {
    private List<MenuResponseDto> menus;

    private MenuListResponseDto(List<MenuResponseDto> menus) {
        this.menus = menus;
    }

    public static MenuListResponseDto from(List<MenuResponseDto> menuResponseDtoList) {
        return new MenuListResponseDto(menuResponseDtoList);
    }
}
