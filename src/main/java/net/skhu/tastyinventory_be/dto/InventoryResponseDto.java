package net.skhu.tastyinventory_be.dto;

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
//        //entity 일부만 사용. 굳이 모든 필드를 가진 생성자 필요X. entity 받아 처리
//        this.id = entity.getId();
//        this.name = entity.getName();
//        this.unit = entity.getUnit();
//        this.imageUrl = entity.getImageUrl();
        return new InventoryResponseDto(entity.getId(), entity.getName(), entity.getUnit(), entity.getImageUrl());
    }
}
