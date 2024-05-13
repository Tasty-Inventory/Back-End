//package net.skhu.tastyinventory_be.service;
//
//import lombok.RequiredArgsConstructor;
//import net.skhu.tastyinventory_be.controller.schedule.dto.ScheduleResponseDto;
//import net.skhu.tastyinventory_be.entity.Schedule;
//import net.skhu.tastyinventory_be.repository.ScheduleRepository;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.time.DayOfWeek;
//
//@RequiredArgsConstructor
//@Service
//public class ScheduleService {
//    private final ScheduleRepository scheduleRepository;
//
//    @Transactional
//    public void saveSchedule(ScheduleCreate scheduleCreate) {
//        Schedule schedule = new Schedule();
//        schedule.setBlockId(scheduleCreate.getBlockId());
//        schedule.setDate(scheduleCreate.getDate());
//        schedule.setDayOfWeek(DayOfWeek.valueOf(scheduleCreate.getDayOfWeek()));
//        scheduleRepository.save(schedule);
//    }
//
//    @Transactional
//    public List<ScheduleResponseDto> getSchedule(){
//        List<Schedule> schedules = scheduleRepository.findAll();
//        List<ScheduleResponseDto> scheduleResponseDtos = new ArrayList<>();
//        for (Schedule schedule : schedules) {
//            ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto();
//            scheduleResponseDto.setId(schedule.getId());
//            scheduleResponseDto.setDayOfWeek(schedule.getDayOfWeek());
//            scheduleResponseDtos.add(scheduleResponseDto);
//
//        }
//        return scheduleResponseDtos;
//    }
//
//    @Transactional(readOnly = true)
//    public ScheduleResponseDto getScheduleDetails(Long id) {
//        Optional<Schedule> scheduleOptional = scheduleRepository.findById(id);
//        if (scheduleOptional.isPresent()) {
//            Schedule schedule = scheduleOptional.get();
//            ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto();
//            scheduleResponseDto.setId(schedule.getId());
//            scheduleResponseDto.setDayOfWeek(schedule.getDayOfWeek());
//
//            return scheduleResponseDto;
//        } else {
//            return null;
//        }
//    }
//
//
//    @Transactional(readOnly = true)
//    public List<ScheduleResponseDto> getSchedulesByEmployeeId(Long employeeId) {
//        List<Schedule> schedules = scheduleRepository.findByEmployeeId(employeeId);
//        List<ScheduleResponseDto> scheduleResponseDtos = new ArrayList<>();
//        for (Schedule schedule : schedules) {
//            ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto();
//            scheduleResponseDto.setId(schedule.getId());
//            scheduleResponseDto.setDayOfWeek(schedule.getDayOfWeek());
//            scheduleResponseDtos.add(scheduleResponseDto);
//        }
//        return scheduleResponseDtos;
//    }
//
//    @Transactional
//    public void deleteById(Long id) {
//        scheduleRepository.deleteById(id);
//    }
//    @Transactional
//    public Optional<Schedule> findById(Long id) {
//        return scheduleRepository.findById(id);
//    }
//}
