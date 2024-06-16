package net.skhu.tastyinventory_be.controller.menu.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MenuRequestDto {
    @NotBlank(message = "이름을 입력하세요.")
    private String name;

    private List<RelatedInventoryRequestDto> relatedInventories;
}
