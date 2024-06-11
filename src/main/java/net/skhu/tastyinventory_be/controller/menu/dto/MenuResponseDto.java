package net.skhu.tastyinventory_be.controller.menu.dto;

import lombok.Getter;
import net.skhu.tastyinventory_be.domain.menu.Menu;

@Getter
public class MenuResponseDto {
    private Long id;
    private String name;

    public MenuResponseDto(Menu menu) {
        this.id = menu.getId();
        this.name = menu.getName();
    }

    public static MenuResponseDto from(Menu menu) {
        return new MenuResponseDto(menu);
    }
}
