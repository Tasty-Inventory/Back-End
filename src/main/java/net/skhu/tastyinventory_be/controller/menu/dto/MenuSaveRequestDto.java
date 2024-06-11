package net.skhu.tastyinventory_be.controller.menu.dto;

import net.skhu.tastyinventory_be.domain.inventory.Inventory;
import net.skhu.tastyinventory_be.domain.menu.Menu;
import net.skhu.tastyinventory_be.domain.recipe.Recipe;

import java.util.Set;

public record MenuSaveRequestDto(
        String name
) {
    public Menu toEntity(){
        return Menu.builder()
                .name(name)
                .build();
    }
}
