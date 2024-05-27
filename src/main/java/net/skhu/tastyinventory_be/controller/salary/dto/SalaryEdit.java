package net.skhu.tastyinventory_be.controller.salary.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class SalaryEdit {
    int id;

    @NotEmpty
    @NotBlank
    String name;

    @NotEmpty
    @NotBlank
    String position;

    @NotEmpty
    @NotBlank
    String baseSalary;

}