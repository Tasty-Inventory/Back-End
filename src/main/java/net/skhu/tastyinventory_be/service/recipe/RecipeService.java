package net.skhu.tastyinventory_be.service.recipe;

import lombok.RequiredArgsConstructor;
import net.skhu.tastyinventory_be.controller.recipe.dto.RecipeSaveRequestDto;
import net.skhu.tastyinventory_be.domain.recipe.Recipe;
import net.skhu.tastyinventory_be.domain.recipe.RecipeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;

    @Transactional
    public void save(RecipeSaveRequestDto requestDto) {
        Recipe recipe = Recipe.builder()
                .usage(requestDto.usage())
                .build();
        recipeRepository.save(recipe);
    }
    public

}
