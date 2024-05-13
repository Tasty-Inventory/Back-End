package net.skhu.tastyinventory_be.domain.salary;
import net.skhu.tastyinventory_be.domain.salary.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SalaryRepository extends JpaRepository<Salary, Long> {
    Optional<Salary> findById(Long id);
}