package net.skhu.tastyinventory_be.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.skhu.tastyinventory_be.domain.inventory.Inventory;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InventorySaveRequestDto {
    private Long id;

    @NotBlank(message = "Please enter inventory name")
    private  String name;

    private  String unit;
    private String imageUrl;

    @Builder
    public InventorySaveRequestDto(String name, String unit, String imageUrl) {
        this.name = name;
        this.unit = unit;
        this.imageUrl = imageUrl;
    }
    public Inventory toEntity(){
        return Inventory.builder()
                .id(id)
                .name(name)
                .unit(unit)
                .imageUrl(imageUrl)
                .build();
    }
}
