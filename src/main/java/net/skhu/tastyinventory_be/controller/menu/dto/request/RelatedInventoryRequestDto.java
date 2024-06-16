package net.skhu.tastyinventory_be.controller.menu.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RelatedInventoryRequestDto {
    @NotNull(message = "재고 아이디를 입력하세요.")
    private Long inventoryId;

    @NotNull(message = "재고 사용량을 입력하세요.")
    private Long inventoryUsage;
}
