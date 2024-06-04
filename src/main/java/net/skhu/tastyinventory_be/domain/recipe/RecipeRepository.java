package net.skhu.tastyinventory_be.domain.recipe;

import net.skhu.tastyinventory_be.domain.menu.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findAllByMenu(Menu menu);
}
