package net.skhu.tastyinventory_be.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;
import java.util.Date;

@Data
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    String rrn; // resident_register_number 주민등록번호
    String phoneNumber;
    String email;
    String address;
    Date hireDate;
    String employmentStatus;
    String bankAccount;
    float totalSalary; // BigDecimal
    String note; // 특이사항

    @OneToMany
    @JoinColumn(name = "scheduleId")
    Schedule schedule;
}
