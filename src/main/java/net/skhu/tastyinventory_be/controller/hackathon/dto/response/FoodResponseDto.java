package net.skhu.tastyinventory_be.controller.hackathon.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FoodResponseDto {
    private Boolean breakfast;
    private Boolean lunch;
    private Boolean dinner;
    private Boolean snack;

    public static FoodResponseDto of(Boolean breakfast, Boolean lunch, Boolean dinner, Boolean snack) {
        return new FoodResponseDto(breakfast, lunch, dinner, snack);
    }
}
