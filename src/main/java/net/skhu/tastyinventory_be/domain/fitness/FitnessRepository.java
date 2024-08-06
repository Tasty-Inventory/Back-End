package net.skhu.tastyinventory_be.domain.fitness;

import net.skhu.tastyinventory_be.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

public interface FitnessRepository extends JpaRepository<Fitness, Long> {
    Optional<Fitness> findByUserAndDate(User user, LocalDate date);
}
