package net.skhu.tastyinventory_be.domain.menu;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.skhu.tastyinventory_be.domain.recipe.Recipe;

import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@Table(name = "Menu")
@Entity
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private  String name;

    @OneToMany(mappedBy = "menu")
    private Set<Recipe> recipes = new HashSet<>();

    @Builder
    public Menu(String name, Set<Recipe> recipes) {
        this.name = name;
        this.recipes = recipes;
    }
    public void update(String name) {
        this.name = name;
    }

}
