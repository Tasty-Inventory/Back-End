package net.skhu.tastyinventory_be.domain.menu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.skhu.tastyinventory_be.domain.BaseEntity;
import net.skhu.tastyinventory_be.domain.recipe.Recipe;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "MENU")
@Entity
public class Menu extends BaseEntity {
    @Column(nullable = false, length = 50)
    private String name;

    private String imageUrl;

    @JsonIgnore
    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Recipe> recipes = new ArrayList<>();

    @Builder
    public Menu(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public void update(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }
}
