package net.skhu.tastyinventory_be.controller.hackathon.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.skhu.tastyinventory_be.domain.fitness.Fitness;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FitnessResponseDto {

    private String name;
    private Integer minute;
    private Integer calories;

    public static FitnessResponseDto from(Fitness fitness) {
        return new FitnessResponseDto(fitness.getName(), fitness.getMinute(), fitness.getCalories());
    }

}
