package net.skhu.tastyinventory_be.domain.inventoryRecord;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.skhu.tastyinventory_be.domain.BaseEntity;
import net.skhu.tastyinventory_be.domain.inventory.Inventory;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "INVENTORY_RECORD")
@Entity
public class InventoryRecord extends BaseEntity {
    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;

    @Column(nullable = false)
    private Long currentVolume;

    private Long orderVolume;

    @Builder
    public InventoryRecord(LocalDate date, Inventory inventory, Long currentVolume, Long orderVolume) {
        this.date = date;
        this.inventory = inventory;
        this.currentVolume = currentVolume;
        this.orderVolume = orderVolume;
    }
}
