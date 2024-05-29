package net.skhu.tastyinventory_be.domain.schedule;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Optional<Schedule> findById(Long id);

    Collection<Object> findByEmployeeId(int employeeId);
}