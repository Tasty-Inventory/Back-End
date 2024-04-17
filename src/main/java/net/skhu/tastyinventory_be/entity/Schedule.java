package net.skhu.tastyinventory_be.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.DayOfWeek;

@Data
@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    int id;
    String monthWeek; // N월 M주차

    @Enumerated(EnumType.STRING)
    DayOfWeek dayOfWeek; // 요일

    @Enumerated(EnumType.STRING)
    TimeSlot timeSlot; // 시간

    @ManyToOne
    @JoinColumn(name = "employeeId")
    Employee employee;

    boolean isActive; // 시간표 체크 여부
}