package net.skhu.tastyinventory_be.controller.sold.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SoldMenuDto {
    @NotNull(message = "메뉴 아이디를 입력하세요")
    private Long menuId;

    @NotNull(message = "메뉴 판매 수량을 입력하세요")
    private Long soldCount;
}
