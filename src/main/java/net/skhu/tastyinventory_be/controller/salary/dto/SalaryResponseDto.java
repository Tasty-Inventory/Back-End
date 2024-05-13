package net.skhu.tastyinventory_be.controller.salary.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.skhu.tastyinventory_be.domain.salary.Salary;

@Builder
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class SalaryResponseDto {
    int id;
    String name;
    String position;
    String baseSalary;

    public static SalaryResponseDto of(Salary salary) {
        return SalaryResponseDto.builder()
                .id(salary.getId())
                .name(salary.getName())
                .position(salary.getPosition())
                .baseSalary(salary.getBaseSalary())
                .build();
    }
}
