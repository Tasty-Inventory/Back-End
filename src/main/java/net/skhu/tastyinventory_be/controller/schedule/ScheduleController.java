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



            scheduleService.save(schedule);
            return ResponseEntity.ok(BaseResponse.success(SuccessCode.SCHEDULE_PATCH_SUCCESS));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(BaseResponse.error(ErrorCode.NOT_FOUND_EMPLOYEE_EXCEPTION));
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteSchedule(@PathVariable(name = "id") Long id) {
        // TODO: Implement deleteSchedule method
        return ResponseEntity.ok(BaseResponse.success(SuccessCode.SCHEDULE_DELETE_SUCCESS));
    }
}