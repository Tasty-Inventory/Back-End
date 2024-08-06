package net.skhu.tastyinventory_be.controller.hackathon.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FitnessRequestDto {
    private String name;
    private Integer minute;
    private Integer calories;
}
