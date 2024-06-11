package net.skhu.tastyinventory_be.controller.inventory.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.skhu.tastyinventory_be.domain.inventory.Inventory;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryResponseDto {
    private Long id;
    private String name;
    private String unit;
    private String imageUrl;


    public static InventoryResponseDto from(Inventory entity) {
        return new InventoryResponseDto(entity.getId(), entity.getName(), entity.getUnit(), entity.getImageUrl());
    }
}
