package net.skhu.tastyinventory_be.controller.recipe.dto;

import lombok.Builder;
import net.skhu.tastyinventory_be.domain.inventory.Inventory;
import net.skhu.tastyinventory_be.domain.menu.Menu;
import net.skhu.tastyinventory_be.domain.recipe.Recipe;

@Builder
public record RecipeInfoResponseDto (
        Long usages,
        Menu menu,
        Inventory inventory
) {
    public static RecipeInfoResponseDto from(Recipe recipe) {
        return RecipeInfoResponseDto.builder()
                .usages(recipe.getUsage())
                .build();
    }
}
