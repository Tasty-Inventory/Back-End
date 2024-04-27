package net.skhu.tastyinventory_be.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scheduleId")
    int id;
    String TimeSlot;

    @Enumerated(EnumType.STRING)
    DayOfWeek dayOfWeek;

    @Enumerated(EnumType.STRING)
    MonthWeek monthWeek;

    // Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "employeeId")
    Employee employee;
}
