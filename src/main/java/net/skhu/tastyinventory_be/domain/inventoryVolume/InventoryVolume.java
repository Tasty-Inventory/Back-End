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

    @Column(nullable = false)
    private Integer friInventory;

    @Column(nullable = false)
    private Integer sunInventory;

    @Column(nullable = false)
    private Integer monOrder;

    @Column(nullable = false)
    private Integer tueOrder;

    @Column(nullable = false)
    private Integer wedOrder;

    @Column(nullable = false)
    private Integer thuOrder;

    @Column(nullable = false)
    private Integer friOrder;

    @Column(nullable = false)
    private Integer satOrder;

    @Column(nullable = false)
    private Integer sunOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;

    @Builder
    public InventoryVolume(Date weekStart, Integer friInventory, Integer sunInventory, Integer monOrder, Integer tueOrder, Integer wedOrder, Integer thuOrder, Integer friOrder, Integer satOrder, Integer sunOrder, Inventory inventory) {
        this.weekStart = weekStart;
        this.friInventory = friInventory;
        this.sunInventory = sunInventory;
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
