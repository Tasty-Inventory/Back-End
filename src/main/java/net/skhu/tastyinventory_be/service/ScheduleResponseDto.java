package net.skhu.tastyinventory_be.service;

import net.skhu.tastyinventory_be.entity.DayOfWeek;
import net.skhu.tastyinventory_be.entity.MonthWeek;
import net.skhu.tastyinventory_be.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Builder
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ScheduleResponseDto {

    int id;
    String TimeSlot;
    DayOfWeek dayOfWeek;
    MonthWeek monthWeek;
    Boolean isActive;
    int employeeId;

    public static ScheduleResponseDto of(Schedule schedule) {
        return ScheduleResponseDto.builder()
                .id(schedule.getId())
                .TimeSlot(schedule.getTimeSlot())
                .dayOfWeek(schedule.getDayOfWeek())
                .monthWeek(schedule.getMonthWeek())
               // .isActive(schedule.getIsActive())
                .employeeId(schedule.getEmployee().getId())
                .build();
    }
}
