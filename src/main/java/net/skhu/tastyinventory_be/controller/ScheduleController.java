package net.skhu.tastyinventory_be.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.skhu.tastyinventory_be.entity.DayOfWeek;
import net.skhu.tastyinventory_be.entity.MonthWeek;
import net.skhu.tastyinventory_be.entity.Salary;
import net.skhu.tastyinventory_be.entity.Schedule;
import net.skhu.tastyinventory_be.exception.model.ScheduleEdit;
import net.skhu.tastyinventory_be.service.SalaryResponseDto;
import net.skhu.tastyinventory_be.service.ScheduleResponseDto;
import net.skhu.tastyinventory_be.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public ResponseEntity<List<ScheduleResponseDto>> findAll() {
        return ResponseEntity.ok(scheduleService.getSchedule());
    }

    @GetMapping("{employeeId}")
    public ResponseEntity<List<ScheduleResponseDto>> getScheduleByEmployeeId(@PathVariable Long employeeId) {
        List<ScheduleResponseDto> schedules = scheduleService.getSchedulesByEmployeeId(employeeId);
        return ResponseEntity.ok(schedules);
    }

    @PostMapping
    public ResponseEntity<String> createOrUpdateSchedule(@Valid @RequestBody ScheduleEdit scheduleEdit, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("입력값이 올바르지 않습니다.");
        }

        Schedule schedule = new Schedule();
        schedule.setTimeSlot(scheduleEdit.getTimeSlot());
        schedule.setDayOfWeek(DayOfWeek.valueOf(scheduleEdit.getDayOfWeek()));
        schedule.setMonthWeek(MonthWeek.valueOf(scheduleEdit.getMonthWeek()));
        // schedule.setIsActive(scheduleEdit.getIsActive());

        scheduleService.save(schedule);

        return ResponseEntity.ok("스케줄 정보 등록/수정");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteSchedule(@PathVariable(name ="id") Long id) {
        Optional<Schedule> scheduleOptional = scheduleService.findById(id);
        if (scheduleOptional.isPresent()) {
            scheduleService.deleteById(id);
            return ResponseEntity.ok("스케줄 정보 삭제");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}