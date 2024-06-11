package net.skhu.tastyinventory_be.controller.menu.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.skhu.tastyinventory_be.domain.inventory.Unit;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RelatedInventoryResponseDto {
    Long inventoryId;
    String inventoryName;
    Long inventoryUsage;
    Unit inventoryUnit;

    public static RelatedInventoryResponseDto of(
            Long inventoryId,
            String inventoryName,
            Long inventoryUsage,
            Unit inventoryUnit
    ) {
        return new RelatedInventoryResponseDto(
                inventoryId,
                inventoryName,
                inventoryUsage,
                inventoryUnit
        );
    }
}
