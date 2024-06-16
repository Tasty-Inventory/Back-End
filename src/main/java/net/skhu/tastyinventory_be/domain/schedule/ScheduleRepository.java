package net.skhu.tastyinventory_be.domain.schedule;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Optional<Schedule> findById(Long id);
    List<Schedule> findByEmployeeId(Long employeeId);
    List<Schedule> findByDateBetweenAndEmployeeId(LocalDate startDate, LocalDate endDate, Long employeeId);

    List<Schedule> findByDateBetween(LocalDate startDate, LocalDate endDate);
}