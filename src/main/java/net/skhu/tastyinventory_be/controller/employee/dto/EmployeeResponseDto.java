package net.skhu.tastyinventory_be.controller.employee.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import net.skhu.tastyinventory_be.domain.employee.Employee;


@Builder
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class EmployeeResponseDto {
    int id;


    String name;

    String phoneNumber;

    String position;

    public static EmployeeResponseDto of(Employee employee) {
        return EmployeeResponseDto.builder()
                .id(employee.getId())
                .name(employee.getName())
                .phoneNumber(employee.getPhoneNumber())
                .position(employee.getPosition())
                .build();
    }

}
