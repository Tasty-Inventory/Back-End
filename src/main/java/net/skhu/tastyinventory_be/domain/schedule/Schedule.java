package net.skhu.tastyinventory_be.domain.schedule;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

@Data
@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scheduleId")
    private Long id;

    @Column(nullable = false)
    private Long employeeId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DayOfWeek dayOfWeek;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TimeSlot timeSlot;

    @Column(nullable = false)
    private LocalDate date;

    @Getter
    public enum DayOfWeek {
        MONDAY("월요일"),
        TUESDAY("화요일"),
        WEDNESDAY("수요일"),
        THURSDAY("목요일"),
        FRIDAY("금요일"),
        SATURDAY("토요일"),
        SUNDAY("일요일");

        private final String value;

        DayOfWeek(String value) {
            this.value = value;
        }

    }

    @Getter
    public enum TimeSlot {
        SLOT_09_10("09:00 - 10:00"),
        SLOT_10_11("10:00 - 11:00"),
        SLOT_11_12("11:00 - 12:00"),
        SLOT_12_13("12:00 - 13:00"),
        SLOT_13_14("13:00 - 14:00"),
        SLOT_14_15("14:00 - 15:00"),
        SLOT_15_16("15:00 - 16:00"),
        SLOT_16_17("16:00 - 17:00"),
        SLOT_17_18("17:00 - 18:00"),
        SLOT_18_19("18:00 - 19:00"),
        SLOT_19_20("19:00 - 20:00"),
        SLOT_20_21("20:00 - 21:00");

        private final String value;

        TimeSlot(String value) {
            this.value = value;
        }

    }
}