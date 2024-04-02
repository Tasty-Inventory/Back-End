package net.skhu.tastyinventory_be.domain.inventoryVolume;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.skhu.tastyinventory_be.domain.inventory.Inventory;

import java.util.Date;


@Getter
@NoArgsConstructor
@Entity
public class InventoryVolume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date weekStart;

    private Integer friInventory;
    private Integer sunInventory;
    private Integer monOrder;
    private Integer tueOrder;
    private Integer wedOrder;
    private Integer thuOrder;
    private Integer friOrder;
    private Integer satOrder;
    private Integer sunOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;

    @Builder
    public InventoryVolume(Long id, Date weekStart, Integer friInventory, Integer sumInventory, Integer monOrder, Integer tueOrder, Integer wedOrder, Integer thuOrder, Integer friOrder, Integer satOrder, Integer sunOrder, Inventory inventory) {
        this.id = id;
        this.weekStart = weekStart;
        this.friInventory = friInventory;
        this.sunInventory = sumInventory;
        this.monOrder = monOrder;
        this.tueOrder = tueOrder;
        this.wedOrder = wedOrder;
        this.thuOrder = thuOrder;
        this.friOrder = friOrder;
        this.satOrder = satOrder;
        this.sunOrder = sunOrder;
        this.inventory = inventory;
    }
}
