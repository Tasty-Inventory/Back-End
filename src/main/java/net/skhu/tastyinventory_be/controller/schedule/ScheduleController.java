package net.skhu.tastyinventory_be.controller.schedule;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.skhu.tastyinventory_be.common.dto.BaseResponse;
import net.skhu.tastyinventory_be.controller.employee.dto.EmployeeResponseDto;
import net.skhu.tastyinventory_be.controller.schedule.dto.ScheduleEdit;
import net.skhu.tastyinventory_be.controller.schedule.dto.ScheduleResponseDto;
import net.skhu.tastyinventory_be.domain.employee.Employee;
import net.skhu.tastyinventory_be.domain.schedule.Schedule;
import net.skhu.tastyinventory_be.exception.ErrorCode;
import net.skhu.tastyinventory_be.exception.SuccessCode;
import net.skhu.tastyinventory_be.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.skhu.tastyinventory_be.common.dto.BaseResponse;
import net.skhu.tastyinventory_be.controller.schedule.dto.ScheduleEdit;
import net.skhu.tastyinventory_be.controller.schedule.dto.ScheduleResponseDto;
import net.skhu.tastyinventory_be.domain.employee.Employee;
import net.skhu.tastyinventory_be.domain.schedule.Schedule;
import net.skhu.tastyinventory_be.exception.ErrorCode;
import net.skhu.tastyinventory_be.exception.SuccessCode;
import net.skhu.tastyinventory_be.service.ScheduleService;
import net.skhu.tastyinventory_be.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("schedule")
@Slf4j
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<BaseResponse<List<ScheduleResponseDto>>> findAll() {
        return ResponseEntity.ok(BaseResponse.success(SuccessCode.GET_SUCCESS, scheduleService.getSchedule()));
    }

    @PostMapping
    public ResponseEntity<?> createSchedule(@Valid @RequestBody ScheduleEdit scheduleEdit) {
        Employee employee = employeeService.findById(scheduleEdit.getEmployeeId());
        if (employee == null) {
            return ResponseEntity.badRequest().body(BaseResponse.error(ErrorCode.NOT_FOUND_EMPLOYEE_EXCEPTION));
        }

        Schedule schedule = new Schedule();
        schedule.setEmployee(employee);
        schedule.setDayOfWeek(scheduleEdit.getDayOfWeek());
        schedule.setTimeSlot(scheduleEdit.getTimeSlot());

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
    public ResponseEntity<BaseResponse<List<ScheduleResponseDto>>> getScheduleByEmployeeId(@PathVariable int employeeId) {
        List<ScheduleResponseDto> schedules = scheduleService.getScheduleByEmployeeId(employeeId);
        return ResponseEntity.ok(BaseResponse.success(SuccessCode.GET_SUCCESS, schedules));
    }

    @PatchMapping("{id}")
    public ResponseEntity<?> editScheduleDetails(@PathVariable(name = "id") Long id, @Valid @RequestBody ScheduleEdit scheduleEdit) {
        // TODO: Implement editScheduleDetails method
        return ResponseEntity.ok(BaseResponse.success(SuccessCode.SCHEDULE_PATCH_SUCCESS));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteSchedule(@PathVariable(name = "id") Long id) {
        // TODO: Implement deleteSchedule method
        return ResponseEntity.ok(BaseResponse.success(SuccessCode.SCHEDULE_DELETE_SUCCESS));
    }
}