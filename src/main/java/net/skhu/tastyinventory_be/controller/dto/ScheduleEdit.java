package net.skhu.tastyinventory_be.controller.dto;

import lombok.Data;

@Data
public class ScheduleEdit {
    long id;

    String timeSlot;
    String monthWeek;
    String dayOfWeek;
    long employeeId;
}
