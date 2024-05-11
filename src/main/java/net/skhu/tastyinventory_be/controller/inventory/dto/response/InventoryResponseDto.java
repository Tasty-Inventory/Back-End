package net.skhu.tastyinventory_be.controller.inventory.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.skhu.tastyinventory_be.domain.inventory.Inventory;
import net.skhu.tastyinventory_be.domain.inventory.Unit;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class InventoryResponseDto {
    private Long inventoryId;
    private String inventoryName;
    private Unit inventoryUnit;
    private String inventoryImage;

    public static InventoryResponseDto from(Inventory inventory) {
        return new InventoryResponseDto(inventory.getId(), inventory.getName(), inventory.getUnit(), inventory.getImageUrl());
    }
}
