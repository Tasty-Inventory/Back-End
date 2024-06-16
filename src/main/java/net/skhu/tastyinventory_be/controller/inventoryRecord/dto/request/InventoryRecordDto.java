package net.skhu.tastyinventory_be.controller.inventoryRecord.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryRecordDto {
    @NotNull(message = "재고 아이디를 입력하세요")
    private Long inventoryId;

    @NotNull(message = "현 재고량을 입력하세요")
    private Long currentVolume;

    @NotNull(message = "발주량을 입력하세요")
    private Long orderVolume;
}
