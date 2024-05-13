package net.skhu.tastyinventory_be.repository;
import net.skhu.tastyinventory_be.entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SalaryRepository extends JpaRepository<Salary, Long> {
    Optional<Salary> findById(Long id);
}