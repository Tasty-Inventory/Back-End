package net.skhu.tastyinventory_be.controller.recipe.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record RecipeListResponseDto (
        List<RecipeInfoResponseDto> recipes
) {
    public static RecipeListResponseDto from(List<RecipeInfoResponseDto> recipes) {
        return RecipeListResponseDto.builder()
                .recipes(recipes)
                .build();
    }
}
