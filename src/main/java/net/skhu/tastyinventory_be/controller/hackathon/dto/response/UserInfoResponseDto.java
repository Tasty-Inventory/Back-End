package net.skhu.tastyinventory_be.controller.hackathon.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserInfoResponseDto {
    private Integer consumedCalories;
    private Integer remainCalories;
    private Integer carbohydrate;
    private Integer protein;
    private Integer fat;

    public static UserInfoResponseDto of(
            Integer consumedCalories,
            Integer remainCalories,
            Integer carbohydrate,
            Integer protein,
            Integer fat
    ) {
        return new UserInfoResponseDto(consumedCalories, remainCalories, carbohydrate, protein, fat);
    }
}
