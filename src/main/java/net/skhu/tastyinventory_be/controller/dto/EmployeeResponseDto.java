package net.skhu.tastyinventory_be.controller.dto;

import lombok.*;
import net.skhu.tastyinventory_be.entity.Employee;


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
