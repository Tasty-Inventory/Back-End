package net.skhu.tastyinventory_be.dto.inventory;

import lombok.Builder;
import java.util.List;

@Builder
public class InventoryListResponseDto {
    private List<InventoryResponseDto> inventories;

    public static InventoryListResponseDto from(List<InventoryResponseDto> inventories){
        return InventoryListResponseDto.builder()
                .inventories(inventories)
                .build();
    }
    public List<InventoryResponseDto> getInventories() {
        return inventories;
    }
}

