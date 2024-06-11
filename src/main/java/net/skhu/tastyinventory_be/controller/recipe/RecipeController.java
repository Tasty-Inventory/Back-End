package net.skhu.tastyinventory_be.controller.recipe;


import lombok.RequiredArgsConstructor;
import net.skhu.tastyinventory_be.common.dto.BaseResponse;
import net.skhu.tastyinventory_be.controller.inventory.dto.InventoryResponseDto;
import net.skhu.tastyinventory_be.controller.recipe.dto.RecipeListResponseDto;
import net.skhu.tastyinventory_be.controller.recipe.dto.RecipeSaveRequestDto;
import net.skhu.tastyinventory_be.exception.SuccessCode;
import net.skhu.tastyinventory_be.service.recipe.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipe")
@RequiredArgsConstructor
public class RecipeController {
    private final RecipeService recipeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse saveRecipe(@RequestBody RecipeSaveRequestDto requestDto) {
        recipeService.save(requestDto);
        return BaseResponse.success(SuccessCode.RECIPE_CREATED_SUCCESS);
    }

    @GetMapping
    public BaseResponse<RecipeListResponseDto> findAllRecipe() {
        RecipeListResponseDto responseDto = recipeService.findAll();
        return BaseResponse.success(SuccessCode.RECIPE_FIND_SUCCESS, responseDto);
    }
}
