package net.skhu.tastyinventory_be.domain.sold;

import net.skhu.tastyinventory_be.domain.menu.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SoldRepository extends JpaRepository<Sold, Long> {
    List<Sold> findByDate(LocalDate date);
    Optional<Sold> findByDateAndMenu(LocalDate date, Menu menu);
}
