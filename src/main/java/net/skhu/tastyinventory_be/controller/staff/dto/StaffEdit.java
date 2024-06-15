package net.skhu.tastyinventory_be.controller.staff.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class StaffEdit {
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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
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