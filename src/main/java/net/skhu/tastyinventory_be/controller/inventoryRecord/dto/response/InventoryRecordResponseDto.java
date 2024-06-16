package net.skhu.tastyinventory_be.controller.inventoryRecord.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.skhu.tastyinventory_be.domain.inventoryRecord.InventoryRecord;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InventoryRecordResponseDto {
    private Long id;
    private String date;
    private Long inventoryId;
    private Long currentVolume;
    private Long orderVolume;

    public InventoryRecordResponseDto(InventoryRecord inventoryRecord) {
        this.id = inventoryRecord.getId();
        this.date = inventoryRecord.getDate().toString();
        this.inventoryId = inventoryRecord.getInventory().getId();
        this.currentVolume = inventoryRecord.getCurrentVolume();
        this.orderVolume = inventoryRecord.getOrderVolume();
    }
}
