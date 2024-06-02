package net.skhu.tastyinventory_be.controller.schedule.dto;
import lombok.Data;
import net.skhu.tastyinventory_be.domain.schedule.Schedule;

@Data
public class ScheduleEdit {
    int id;

    Long employeeId;
    private Schedule.DayOfWeek dayOfWeek;
    private Schedule.TimeSlot timeSlot;


}