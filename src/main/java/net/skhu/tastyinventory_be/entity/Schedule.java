package net.skhu.tastyinventory_be.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String startTime;
    String endTime;
    Date date;

    @ManyToOne
    @JoinColumn(name = "employeeId")
    Employee employee;

}
