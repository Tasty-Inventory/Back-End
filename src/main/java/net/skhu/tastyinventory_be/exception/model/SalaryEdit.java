package net.skhu.tastyinventory_be.exception.model;

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