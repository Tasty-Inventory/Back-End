package net.skhu.tastyinventory_be.domain.recipe;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.skhu.tastyinventory_be.domain.BaseEntity;
import net.skhu.tastyinventory_be.domain.inventory.Inventory;
import net.skhu.tastyinventory_be.domain.menu.Menu;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "RECIPE")
@Entity
public class Recipe extends BaseEntity {
    @Column(nullable = false)
    private Long usages;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;

    @Builder
    public Recipe(Long usages, Menu menu, Inventory inventory) {
        this.usages = usages;
        this.menu = menu;
        this.inventory = inventory;
    }
}
