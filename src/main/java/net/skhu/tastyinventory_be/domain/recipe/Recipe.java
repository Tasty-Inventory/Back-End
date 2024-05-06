package net.skhu.tastyinventory_be.domain.recipe;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.skhu.tastyinventory_be.domain.inventory.Inventory;
import net.skhu.tastyinventory_be.domain.menu.Menu;

@Getter
@NoArgsConstructor
@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "menuId")
    private Menu menu;

    @ManyToOne
    @JoinColumn(name = "inventoryId", referencedColumnName = "id")
    private Inventory inventory;

    private Integer usages;  //재료 사용량

    @Builder
    public Recipe(Menu menu, Inventory inventory, Integer usages) {
        this.menu = menu;
        this.inventory = inventory;
        this.usages = usages;
    }

}
