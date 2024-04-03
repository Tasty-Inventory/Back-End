package net.skhu.tastyinventory_be.domain.inventory;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.skhu.tastyinventory_be.domain.inventoryVolume.InventoryVolume;
import net.skhu.tastyinventory_be.domain.recipe.Recipe;

import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@Entity
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 45)
    private  String name;

    @Column(length = 45)
    private  String unit;

    @OneToMany(mappedBy = "inventory")
    private Set<Recipe> recipes = new HashSet<>();

    @OneToMany(mappedBy = "inventory")
    private Set<InventoryVolume> inventoryVolumes = new HashSet<>();


    @Builder
    public Inventory(String name, String unit, Set<InventoryVolume> inventoryVolumes) {
        this.name = name;
        this.unit = unit;
        this.inventoryVolumes = inventoryVolumes != null ? inventoryVolumes : new HashSet<>();
    }
}
