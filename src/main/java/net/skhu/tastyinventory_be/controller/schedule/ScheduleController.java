package net.skhu.tastyinventory_be.controller.schedule;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.skhu.tastyinventory_be.common.dto.BaseResponse;
import net.skhu.tastyinventory_be.controller.schedule.dto.ScheduleEdit;
import net.skhu.tastyinventory_be.controller.schedule.dto.ScheduleResponseDto;
import net.skhu.tastyinventory_be.domain.schedule.Schedule;
import net.skhu.tastyinventory_be.exception.ErrorCode;
import net.skhu.tastyinventory_be.exception.SuccessCode;
import net.skhu.tastyinventory_be.service.ScheduleService;
import net.skhu.tastyinventory_be.util.WeekUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("schedule")
@Slf4j
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping
    public ResponseEntity<BaseResponse<List<ScheduleResponseDto>>> findAll() {
        return ResponseEntity.ok(BaseResponse.success(SuccessCode.GET_SUCCESS, scheduleService.getSchedule()));
    }

    @PostMapping
    public ResponseEntity<?> createSchedule(@Valid @RequestBody ScheduleEdit scheduleEdit) {
        Schedule schedule = new Schedule();
        schedule.setEmployeeId(scheduleEdit.getEmployeeId());
        schedule.setDayOfWeek(scheduleEdit.getDayOfWeek());
        schedule.setTimeSlot(scheduleEdit.getTimeSlot());
        schedule.setDate(scheduleEdit.getDate());
        scheduleService.save(schedule);

        return ResponseEntity.ok(BaseResponse.success(SuccessCode.SCHEDULE_CREATE_SUCCESS));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getScheduleDetails(@PathVariable(name = "id") Long id) {
        ScheduleResponseDto schedule = scheduleService.getScheduleDetails(id);
        if (schedule != null) {
            return ResponseEntity.ok(BaseResponse.success(SuccessCode.GET_SUCCESS, schedule));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<?> getEmployeeScheduleDetails(@PathVariable(name = "employeeId") Long employeeId) {
        List<ScheduleResponseDto> schedules = scheduleService.getEmployeeScheduleDetails(employeeId);
        if (!schedules.isEmpty()) {
            return ResponseEntity.ok(BaseResponse.success(SuccessCode.GET_SUCCESS, schedules));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(BaseResponse.error(ErrorCode.NOT_FOUND_EMPLOYEE_EXCEPTION));
        }
    }

    @PatchMapping("{id}")
    public ResponseEntity<?> editScheduleDetails(@PathVariable(name ="id") Long id, @Valid @RequestBody ScheduleEdit scheduleEdit) {


        Optional<Schedule> scheduleOptional = scheduleService.findById(id);
        if (scheduleOptional.isPresent()) {
            Schedule schedule = scheduleOptional.get();

            if (scheduleEdit.getDayOfWeek() != null) {
                schedule.setDayOfWeek(scheduleEdit.getDayOfWeek());
            }
            if (scheduleEdit.getTimeSlot() != null) {
                schedule.setTimeSlot(scheduleEdit.getTimeSlot());
            }
            if (scheduleEdit.getDate() != null) {
                schedule.setDate(scheduleEdit.getDate());
            }



            scheduleService.save(schedule);
            return ResponseEntity.ok(BaseResponse.success(SuccessCode.SCHEDULE_PATCH_SUCCESS));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(BaseResponse.error(ErrorCode.NOT_FOUND_SCHEDULE_EXCEPTION));
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteSchedule(@PathVariable(name ="id") Long id) {
        Optional<Schedule> scheduleOptional = scheduleService.findById(id);
        if (scheduleOptional.isPresent()) {
            scheduleService.deleteById(id);
            return ResponseEntity.ok(BaseResponse.success(SuccessCode.SCHEDULE_DELETE_SUCCESS));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(BaseResponse.error(ErrorCode.NOT_FOUND_SCHEDULE_EXCEPTION));
        }
    }

    @GetMapping("/week")
    public ResponseEntity<BaseResponse<List<ScheduleResponseDto>>> getWeekSchedules(
            @RequestParam int year,
            @RequestParam int month,
            @RequestParam int week,
            @RequestParam(required = false) Long employeeId) {
        List<ScheduleResponseDto> schedules = scheduleService.getSchedulesForWeek(year, month, week, employeeId);
        return ResponseEntity.ok(BaseResponse.success(SuccessCode.GET_SUCCESS, schedules));
    }

    @GetMapping("/week/next")
    public ResponseEntity<BaseResponse<List<ScheduleResponseDto>>> getNextWeekSchedules(
            @RequestParam int year,
            @RequestParam int month,
            @RequestParam int week,
            @RequestParam(required = false) Long employeeId) {
        int nextWeek = WeekUtils.changeWeek(year, month, week, 1);
        List<ScheduleResponseDto> schedules = scheduleService.getSchedulesForWeek(year, month, nextWeek, employeeId);
        return ResponseEntity.ok(BaseResponse.success(SuccessCode.GET_SUCCESS, schedules));
    }

    @GetMapping("/week/previous")
    public ResponseEntity<BaseResponse<List<ScheduleResponseDto>>> getPreviousWeekSchedules(
            @RequestParam int year,
            @RequestParam int month,
            @RequestParam int week,
            @RequestParam(required = false) Long employeeId) {
        int previousWeek = WeekUtils.changeWeek(year, month, week, -1);
        List<ScheduleResponseDto> schedules = scheduleService.getSchedulesForWeek(year, month, previousWeek, employeeId);
        return ResponseEntity.ok(BaseResponse.success(SuccessCode.GET_SUCCESS, schedules));
    }
}