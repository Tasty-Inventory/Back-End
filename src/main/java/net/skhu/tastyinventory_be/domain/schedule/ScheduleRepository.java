//package net.skhu.tastyinventory_be.repository;
//import net.skhu.tastyinventory_be.entity.Schedule;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.List;
//import java.util.Optional;
//
//public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
//    Optional<Schedule> findById(Long id);
//
//    List<Schedule> findByEmployeeId(Long employeeId);
//}