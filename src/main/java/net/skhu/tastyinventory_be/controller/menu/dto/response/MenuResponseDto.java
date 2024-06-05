package net.skhu.tastyinventory_be.controller.menu.dto.response;

import net.skhu.tastyinventory_be.controller.inventory.dto.response.InventoryResponseDto;

import java.util.List;

public record MenuResponseDto(
        String name,
        String imageUrl,
        List<InventoryResponseDto> inventories
) {
    public static MenuResponseDto of(String name, String imageUrl, List<InventoryResponseDto> inventories) {
        return new MenuResponseDto(name, imageUrl, inventories);
    }
}
