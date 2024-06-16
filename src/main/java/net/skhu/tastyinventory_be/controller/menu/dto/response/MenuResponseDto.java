package net.skhu.tastyinventory_be.controller.menu.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MenuResponseDto{
    Long menuId;
    String menuName;
    String menuImage;

    public static MenuResponseDto of(Long menuId, String menuName, String menuImage) {
        return new MenuResponseDto(menuId, menuName, menuImage);
    }
}
