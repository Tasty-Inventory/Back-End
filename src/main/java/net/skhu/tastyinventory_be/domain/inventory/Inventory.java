package net.skhu.tastyinventory_be.domain.inventory;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.skhu.tastyinventory_be.domain.inventoryVolume.InventoryVolume;
import net.skhu.tastyinventory_be.domain.recipe.Recipe;
import net.skhu.tastyinventory_be.dto.InventoryUpdateRequestDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@Table(name = "inventory")
@Entity
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 45)
    private  String name;

    @Column(length = 45)
    private  String unit;

    private String imageUrl;

    //    @OneToMany(mappedBy = "inventory")
//    private Set<Recipe> recipes = new HashSet<>();
//
//    @OneToMany(mappedBy = "inventory")
//    private Set<InventoryVolume> inventoryVolumes = new HashSet<>();
    // net.skhu.tastyinventory_be.domain.inventory.Inventory 클래스 내부

    public void update(String name, String unit, String imageUrl) {
        this.name = name;
        this.unit = unit;
        this.imageUrl = imageUrl;
    }

}
