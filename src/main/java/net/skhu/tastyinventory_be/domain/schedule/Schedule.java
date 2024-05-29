package net.skhu.tastyinventory_be.domain.schedule;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import net.skhu.tastyinventory_be.domain.employee.Employee;

@Data
@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scheduleId")
    private int id;

    public int getEmployeeId() {
        return employee.getId();
    }

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DayOfWeek dayOfWeek;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TimeSlot timeSlot;

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
        TIME_09_10("09:00 - 10:00"),
        TIME_10_11("10:00 - 11:00"),
        TIME_11_12("11:00 - 12:00"),
        TIME_12_13("12:00 - 13:00"),
        TIME_13_14("13:00 - 14:00"),
        TIME_14_15("14:00 - 15:00"),
        TIME_15_16("15:00 - 16:00"),
        TIME_16_17("16:00 - 17:00"),
        TIME_17_18("17:00 - 18:00"),
        TIME_18_19("18:00 - 19:00"),
        TIME_19_20("19:00 - 20:00"),
        TIME_20_21("20:00 - 21:00");

        private final String value;

        TimeSlot(String value) {
            this.value = value;
        }
    }
}