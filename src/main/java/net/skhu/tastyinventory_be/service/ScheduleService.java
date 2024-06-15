package net.skhu.tastyinventory_be.service;

import lombok.RequiredArgsConstructor;
import net.skhu.tastyinventory_be.controller.schedule.dto.ScheduleResponseDto;
import net.skhu.tastyinventory_be.domain.schedule.Schedule;
import net.skhu.tastyinventory_be.domain.schedule.ScheduleRepository;
import net.skhu.tastyinventory_be.util.WeekUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public void save(Schedule schedule) {
        scheduleRepository.save(schedule);
    }

    @Transactional
    public List<ScheduleResponseDto> getSchedule(){
        List<Schedule> schedules = scheduleRepository.findAll();
        List<ScheduleResponseDto> scheduleResponseDtos = new ArrayList<>();
        for (Schedule schedule : schedules) {
            ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto();
            scheduleResponseDto.setId(schedule.getId());
            scheduleResponseDto.setEmployeeId(schedule.getEmployeeId());
            scheduleResponseDto.setDayOfWeek(schedule.getDayOfWeek());
            scheduleResponseDto.setTimeSlot(schedule.getTimeSlot());
            scheduleResponseDto.setDate(schedule.getDate());

            scheduleResponseDtos.add(scheduleResponseDto);
        }
        return scheduleResponseDtos;
    }

    @Transactional(readOnly = true)
    public ScheduleResponseDto getScheduleDetails(Long id) {
        Optional<Schedule> scheduleOptional = scheduleRepository.findById(id);
        if (scheduleOptional.isPresent()) {
            Schedule schedule = scheduleOptional.get();
            ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto();
            scheduleResponseDto.setId(schedule.getId());
            scheduleResponseDto.setEmployeeId(schedule.getEmployeeId());
            scheduleResponseDto.setDayOfWeek(schedule.getDayOfWeek());
            scheduleResponseDto.setTimeSlot(schedule.getTimeSlot());
            scheduleResponseDto.setDate(schedule.getDate());

            return scheduleResponseDto;
        } else {
            return null;
        }
    }

    @Transactional(readOnly = true)
    public List<ScheduleResponseDto> getEmployeeScheduleDetails(Long employeeId) {
        List<Schedule> schedules = scheduleRepository.findByEmployeeId(employeeId);
        return schedules.stream()
                .map(schedule -> new ScheduleResponseDto(
                        schedule.getId(),
                        schedule.getEmployeeId(),
                        schedule.getDayOfWeek(),
                        schedule.getTimeSlot(),
                        schedule.getDate()
                ))
                .collect(Collectors.toList());
    }


    @Transactional
    public void deleteById(Long id) {
        scheduleRepository.deleteById(id);
    }
    @Transactional
    public Optional<Schedule> findById(Long id) {
        return scheduleRepository.findById(id);
    }

    public List<ScheduleResponseDto> getSchedulesForWeek(int year, int month, int week, Long employeeId) {
        LocalDate startOfWeek = WeekUtils.getStartOfWeek(year, month, week);
        LocalDate endOfWeek = startOfWeek.plusDays(6);

        List<Schedule> schedules;
        if (employeeId != null) {
            schedules = scheduleRepository.findByDateBetweenAndEmployeeId(startOfWeek, endOfWeek, employeeId);
        } else {
            schedules = scheduleRepository.findByDateBetween(startOfWeek, endOfWeek);
        }

        return schedules.stream()
                .map(this::convertToDto)
                .toList();
    }

    private ScheduleResponseDto convertToDto(Schedule schedule) {
        return new ScheduleResponseDto(
                schedule.getId(),
                schedule.getEmployeeId(),
                schedule.getDayOfWeek(),
                schedule.getTimeSlot(),
                schedule.getDate()
        );
    }
}