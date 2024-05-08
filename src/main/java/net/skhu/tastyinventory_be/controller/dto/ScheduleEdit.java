package net.skhu.tastyinventory_be.controller.dto;

import lombok.Data;

@Data
public class ScheduleEdit {
    long id;

    String dayOfWeek;
    long employeeId;
}
