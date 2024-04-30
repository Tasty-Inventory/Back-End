package net.skhu.tastyinventory_be.controller.dto;

import lombok.Data;

@Data
public class ScheduleEdit {


    String timeSlot;
    String dayOfWeek;
    String monthWeek;
    long employeeId;
}
