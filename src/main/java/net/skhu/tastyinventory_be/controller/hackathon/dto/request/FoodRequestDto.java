package net.skhu.tastyinventory_be.controller.hackathon.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FoodRequestDto {
    private String name;
    private Integer carbohydrate;
    private Integer protein;
    private Integer fat;
}
