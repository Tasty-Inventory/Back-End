package net.skhu.tastyinventory_be.dto.inventory;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
public class InventoryVolumeRequestDto {
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
    private Long inventoryId;

    @Builder
    public InventoryVolumeRequestDto(Date weekStart, Integer friInventory, Integer sunInventory, Integer monOrder,
                                     Integer tueOrder, Integer wedOrder, Integer thuOrder, Integer friOrder, Integer satOrder, Integer sunOrder, Long inventoryId){
        this.weekStart = weekStart;
        this.friInventory = friInventory;
        this.sunInventory = sunInventory;
        this.monOrder = monOrder;
        this.tueOrder = tueOrder;
        this.wedOrder = wedOrder;
        this.thuOrder = thuOrder;
        this.satOrder = satOrder;
        this.friOrder = friOrder;
        this.sunOrder = sunOrder;
        this.inventoryId = inventoryId;
    }
}