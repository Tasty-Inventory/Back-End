package net.skhu.tastyinventory_be.controller.schedule.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    Long id;

    @JsonProperty("employee_id")
    Long employeeId;

    Schedule.DayOfWeek dayOfWeek;
    Schedule.TimeSlot timeSlot;
    java.util.Date date;

    public static ScheduleResponseDto of(Schedule schedule) {
        return ScheduleResponseDto.builder()
                .id(schedule.getId())
                .employeeId(schedule.getEmployeeId())
                .dayOfWeek(schedule.getDayOfWeek())
                .timeSlot(schedule.getTimeSlot())
                .date(schedule.getDate())
                .build();

    }
}