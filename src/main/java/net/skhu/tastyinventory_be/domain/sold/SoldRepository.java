package net.skhu.tastyinventory_be.domain.sold;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface SoldRepository extends JpaRepository<Sold, Long> {
    List<Sold> findByDate(LocalDate date);
}
