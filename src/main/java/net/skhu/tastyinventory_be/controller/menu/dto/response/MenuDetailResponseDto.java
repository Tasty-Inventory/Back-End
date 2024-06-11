package net.skhu.tastyinventory_be.controller.menu.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MenuDetailResponseDto{
    Long menuId;
    String menuName;
    String menuImage;
    List<RelatedInventoryResponseDto> relatedInventories;

    public static MenuDetailResponseDto of(
            Long menuId,
            String menuName,
            String menuImage,
            List<RelatedInventoryResponseDto> relatedInventories
    ) {
        return new MenuDetailResponseDto(
                menuId,
                menuName,
                menuImage,
                relatedInventories
        );
    }
}

