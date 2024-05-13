//package net.skhu.tastyinventory_be.controller.schedule.dto;
//
//import net.skhu.tastyinventory_be.entity.Schedule;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.RequiredArgsConstructor;
//import java.time.DayOfWeek;
//import java.time.LocalDateTime;
//
//@Builder
//@Data
//@RequiredArgsConstructor
//@AllArgsConstructor
//public class ScheduleResponseDto {
//    long id;
//    DayOfWeek dayOfWeek;
//    int blockId;
//    LocalDateTime date;
//
//    public static ScheduleResponseDto of(Schedule schedule) {
//        return ScheduleResponseDto.builder()
//                .id(schedule.getId())
//                .dayOfWeek(schedule.getDayOfWeek())
//                .date(schedule.getDate())
//                .build();
//    }
//}
