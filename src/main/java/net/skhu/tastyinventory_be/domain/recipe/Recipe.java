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

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "menuId")
//    private Menu menu;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "inventoryId", referencedColumnName = "id")
//    private Inventory inventory;

    @Column(nullable = false)
    private Integer usage;  //재료 사용량

    @Builder
    public Recipe(Integer usage) {  //Menu menu, Inventory inventory,
//        this.menu = menu;
//        this.inventory = inventory;
        this.usage = usage;
    }

}
