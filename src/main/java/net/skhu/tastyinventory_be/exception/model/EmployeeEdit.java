package net.skhu.tastyinventory_be.exception.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import java.util.Date;

@Data
public class EmployeeEdit {
    int id;

    @NotEmpty
    @NotBlank
    String name;

    @NotEmpty
    @NotBlank
    String rrn;

    @NotEmpty
    @NotBlank
    String phoneNumber;

    @NotEmpty
    @NotBlank
    String email;

    @NotEmpty
    @NotBlank
    String address;

    @NotEmpty
    @NotBlank
    String position;

    @NotEmpty
    @NotBlank
    Date hireDate;

    @NotEmpty
    @NotBlank
    String employmentStatus;

    @NotEmpty
    @NotBlank
    String bankAccount;

    @NotEmpty
    @NotBlank
    String note;

}