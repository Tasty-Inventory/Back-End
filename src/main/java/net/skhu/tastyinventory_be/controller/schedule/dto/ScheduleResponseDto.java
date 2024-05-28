package net.skhu.tastyinventory_be.controller.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.skhu.tastyinventory_be.domain.schedule.Schedule;

@Builder
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ScheduleResponseDto {
    int id;
    Schedule.DayOfWeek dayOfWeek;
    Schedule.TimeSlot timeSlot;

    public static ScheduleResponseDto of(Schedule schedule) {
        return ScheduleResponseDto.builder()
                .id(schedule.getId())
                .dayOfWeek(schedule.getDayOfWeek())
                .timeSlot(schedule.getTimeSlot())
                .build();
    }
}