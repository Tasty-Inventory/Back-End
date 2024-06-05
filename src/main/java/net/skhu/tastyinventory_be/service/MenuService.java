package net.skhu.tastyinventory_be.service;

import lombok.RequiredArgsConstructor;
import net.skhu.tastyinventory_be.controller.inventory.dto.response.InventoryResponseDto;
import net.skhu.tastyinventory_be.controller.menu.dto.response.MenuResponseDto;
import net.skhu.tastyinventory_be.domain.menu.Menu;
import net.skhu.tastyinventory_be.domain.menu.MenuRepository;
import net.skhu.tastyinventory_be.domain.recipe.RecipeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MenuService {
    private final MenuRepository menuRepository;
    private final RecipeRepository recipeRepository;

    public List<MenuResponseDto> findAll() {
        List<Menu> menuList = menuRepository.findAll();
        List<MenuResponseDto> result = new ArrayList<>();
        for (Menu menu : menuList) {
            result.add(MenuResponseDto.of(
                    menu.getName(),
                    menu.getImageUrl(),
                    recipeRepository.findAllByMenu(menu)
                            .stream()
                            .map(recipe -> InventoryResponseDto.from(recipe.getInventory()))
                            .collect(Collectors.toList())
            ));
        }
        return result;
    }
}
