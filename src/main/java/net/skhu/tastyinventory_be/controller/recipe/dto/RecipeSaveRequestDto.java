package net.skhu.tastyinventory_be.controller.recipe.dto;

import net.skhu.tastyinventory_be.domain.inventory.Inventory;
import net.skhu.tastyinventory_be.domain.menu.Menu;

public record RecipeSaveRequestDto(
        Long usage,
        Menu menu,
        Inventory inventory
) {
}
