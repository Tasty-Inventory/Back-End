package net.skhu.tastyinventory_be.exception.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import net.skhu.tastyinventory_be.entity.TimeSlot;

import java.time.DayOfWeek;

@Data
public class ScheduleEdit {
    int id;

    String timeSlot;
    String dayOfWeek;
    String monthWeek;
    Boolean isActive;

}
