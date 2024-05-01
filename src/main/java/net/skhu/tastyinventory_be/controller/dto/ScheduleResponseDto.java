package net.skhu.tastyinventory_be.controller.dto;

import net.skhu.tastyinventory_be.entity.DayOfWeek;
import net.skhu.tastyinventory_be.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ScheduleResponseDto {

    String TimeSlot;
    DayOfWeek dayOfWeek;
    int blockId;
    LocalDateTime date;
    int employeeId;

    public static ScheduleResponseDto of(Schedule schedule) {
        return ScheduleResponseDto.builder()
                .id(schedule.getId())
                .TimeSlot(schedule.getTimeSlot())
                .dayOfWeek(schedule.getDayOfWeek())
                .monthWeek(schedule.getMonthWeek())
                .employeeId(schedule.getEmployee().getId())
                .build();
    }
}
