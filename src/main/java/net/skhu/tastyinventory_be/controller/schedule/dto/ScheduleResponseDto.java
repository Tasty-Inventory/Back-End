package net.skhu.tastyinventory_be.controller.schedule.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.skhu.tastyinventory_be.domain.schedule.Schedule;

import java.time.LocalDate;

@Builder
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ScheduleResponseDto {
    Long id;

    @JsonProperty("staff_id")
    Long staffId;

    Schedule.DayOfWeek dayOfWeek;
    Schedule.TimeSlot timeSlot;
    LocalDate date;

    public static ScheduleResponseDto of(Schedule schedule) {
        return ScheduleResponseDto.builder()
                .id(schedule.getId())
                .staffId(schedule.getStaffId())
                .dayOfWeek(schedule.getDayOfWeek())
                .timeSlot(schedule.getTimeSlot())
                .date(schedule.getDate())
                .build();

    }
}