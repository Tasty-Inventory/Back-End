package net.skhu.tastyinventory_be.domain.inventory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.skhu.tastyinventory_be.domain.BaseEntity;
import net.skhu.tastyinventory_be.domain.menu.Menu;
import net.skhu.tastyinventory_be.domain.recipe.Recipe;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "INVENTORY")
@Entity
public class Inventory extends BaseEntity {
    @Column(nullable = false, length = 50)
    private String name;

    @Enumerated(value = EnumType.STRING)
    private Unit unit;

    private String imageUrl;

    @JsonIgnore
    @OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Recipe> recipes = new ArrayList<>();

    @Builder
    public Inventory(String name, Unit unit, String imageUrl) {
        this.name = name;
        this.unit = unit;
        this.imageUrl = imageUrl;
    }

    public void update(String name, Unit unit, String imageUrl) {
        this.name = name;
        this.unit = unit;
        this.imageUrl = imageUrl;
    }
}
