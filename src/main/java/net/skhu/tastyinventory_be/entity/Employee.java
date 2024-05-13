package net.skhu.tastyinventory_be.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employeeId")
    int id;
    String name;
    String rrn; // resident_register_number 주민등록번호
    String phoneNumber;
    String email;
    String address;
    String position;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date hireDate;

    String employmentStatus;
    String bankAccount;

    String note; // 특이사항

   @OneToMany
   List<Schedule> schedules = new ArrayList<>();
}
