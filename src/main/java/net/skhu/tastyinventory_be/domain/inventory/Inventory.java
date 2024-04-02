package net.skhu.tastyinventory_be.domain.inventory;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.skhu.tastyinventory_be.domain.inventoryVolume.InventoryVolume;

import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@Entity
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  String name;
    private  String unit;

    @OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<InventoryVolume> inventoryVolumes = new HashSet<>();


    @Builder
    public Inventory(Long id, String name, String unit, Set<InventoryVolume> inventoryVolumes) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.inventoryVolumes = inventoryVolumes != null ? inventoryVolumes : new HashSet<>();
    }
}
