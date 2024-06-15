package net.skhu.tastyinventory_be.controller.schedule.dto;
import lombok.Data;
import net.skhu.tastyinventory_be.domain.schedule.Schedule;
import java.time.LocalDate;

@Data
public class ScheduleEdit {
    int id;

    Long staffId;
    private Schedule.DayOfWeek dayOfWeek;
    private Schedule.TimeSlot timeSlot;
    private LocalDate date;

}