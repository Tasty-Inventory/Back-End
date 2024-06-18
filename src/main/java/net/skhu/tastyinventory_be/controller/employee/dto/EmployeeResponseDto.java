package net.skhu.tastyinventory_be.controller.employee.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import net.skhu.tastyinventory_be.domain.employee.Employee;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;

@Builder
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class EmployeeResponseDto {
    int id;

    String name;

    String rrn;

    String phoneNumber;

    String email;

    String address;

    String position;

    String hireDate;

    String employmentStatus;

    String bankAccount;

    String note;

    public static EmployeeResponseDto of(Employee employee) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return EmployeeResponseDto.builder()
                .id(employee.getId())
                .name(employee.getName())
                .rrn(employee.getRrn())
                .phoneNumber(employee.getPhoneNumber())
                .email(employee.getEmail())
                .address(employee.getAddress())
                .position(employee.getPosition())
                .hireDate(employee.getHireDate() != null ? sdf.format(employee.getHireDate()) : null)
                .employmentStatus(employee.getEmploymentStatus())
                .bankAccount(employee.getBankAccount())
                .note(employee.getNote())
                .build();
    }
}
