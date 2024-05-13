package net.skhu.tastyinventory_be.domain.salary;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "salaryId")
    int id;
    String name;
    String position;
    String baseSalary;

}
