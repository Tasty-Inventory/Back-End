package net.skhu.tastyinventory_be.controller.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ScheduleEdit {
    long id;
    long blockId;
    LocalDateTime date;
    String dayOfWeek;
    long employeeId;
}
