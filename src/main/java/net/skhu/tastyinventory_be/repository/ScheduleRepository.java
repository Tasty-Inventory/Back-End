package net.skhu.tastyinventory_be.repository;

import net.skhu.tastyinventory_be.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByEmployeeId(Long employeeId);
    List<Schedule> findByMonthWeek(String monthWeek);

    Schedule findByEmployeeIdAndMonthWeek(Long employeeId, String monthWeek);
}