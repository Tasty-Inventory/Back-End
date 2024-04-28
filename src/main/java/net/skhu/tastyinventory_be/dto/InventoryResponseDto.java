package net.skhu.tastyinventory_be.dto;

import lombok.Getter;
import net.skhu.tastyinventory_be.domain.inventory.Inventory;

@Getter
public class InventoryResponseDto {
    private Long id;
    private String name;
    private String unit;
    private String imageUrl;

    public InventoryResponseDto(Inventory entity) { //entity 일부만 사용. 굳이 모든 필드를 가진 생성자 필요X. entity 받아 처리
        this.id = entity.getId();
        this.name = entity.getName();
        this.unit = entity.getUnit();
        this.imageUrl = entity.getImageUrl();
    }
}
