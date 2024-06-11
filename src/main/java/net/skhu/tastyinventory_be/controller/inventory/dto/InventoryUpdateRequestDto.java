package net.skhu.tastyinventory_be.controller.inventory.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.skhu.tastyinventory_be.domain.inventory.Inventory;

@Getter
@NoArgsConstructor
public class InventoryUpdateRequestDto {
    private String name;
    private String unit;
    private String imageUrl;

    @Builder
    public InventoryUpdateRequestDto(String name, String unit, String imageUrl) {
        this.name = name;
        this.unit = unit;
        this.imageUrl = imageUrl;
    }

}
