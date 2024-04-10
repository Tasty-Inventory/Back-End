package net.skhu.tastyinventory_be.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.skhu.tastyinventory_be.domain.inventory.Inventory;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryDto {
    private Long id;
    private  String name;
    private  String unit;
    private String imageUrl;

    @Builder
    public InventoryDto(String name, String unit, String imageUrl) {
        this.name = name;
        this.unit = unit;
        this.imageUrl = imageUrl;
    }
    public Inventory toEntity(){
        return Inventory.builder()
                .name(name)
                .unit(unit)
                .imageUrl(imageUrl)
                .build();
    }
}
