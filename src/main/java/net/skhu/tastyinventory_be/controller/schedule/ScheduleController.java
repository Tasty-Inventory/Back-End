//package net.skhu.tastyinventory_be.controller.schedule;
//
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import net.skhu.tastyinventory_be.entity.*;
//import net.skhu.tastyinventory_be.controller.schedule.dto.ScheduleEdit;
//import net.skhu.tastyinventory_be.controller.schedule.dto.ScheduleResponseDto;
//import net.skhu.tastyinventory_be.service.ScheduleService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RequiredArgsConstructor
//@RestController
//@RequestMapping("schedule")
//@Slf4j
//public class ScheduleController {
//
//    private final ScheduleService scheduleService;
//
//    @GetMapping
//    public ResponseEntity<List<ScheduleResponseDto>> findAll() {
//        return ResponseEntity.ok(scheduleService.getSchedule());
//    }
//
//    @GetMapping("{employeeId}")
//    public ResponseEntity<List<ScheduleResponseDto>> getScheduleByEmployeeId(@PathVariable Long employeeId) {
//        List<ScheduleResponseDto> schedules = scheduleService.getSchedulesByEmployeeId(employeeId);
//        return ResponseEntity.ok(schedules);
//    }
//
//    @PostMapping
//    public ResponseEntity<String> createSchedule(@Valid @RequestBody ScheduleCreate scheduleCreate, BindingResult bindingResult, Model model) {
//        if (bindingResult.hasErrors()) {
//            return ResponseEntity.badRequest().body("입력값이 올바르지 않습니다.");
//        }
//
//        return ResponseEntity.created("스케줄 정보 등록", scheduleService.saveSchedule(schedule));
//    }
//
//    @PatchMapping
//    public ResponseEntity<String> updateSchedule(@Valid @RequestBody ScheduleEdit scheduleEdit, BindingResult bindingResult, Model model) {
//        if (bindingResult.hasErrors()) {
//            return ResponseEntity.badRequest().body("입력값이 올바르지 않습니다.");
//        }
//
//        return ResponseEntity.ok("스케줄 정보 수정");
//    }
//
//    @DeleteMapping("{id}")
//    public ResponseEntity<String> deleteSchedule(@PathVariable(name ="id") Long id) {
//        Optional<Schedule> scheduleOptional = scheduleService.findById(id);
//        if (scheduleOptional.isPresent()) {
//            scheduleService.deleteById(id);
//            return ResponseEntity.ok("스케줄 정보 삭제");
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//}