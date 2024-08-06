package net.skhu.tastyinventory_be.domain.weight;

import net.skhu.tastyinventory_be.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface WeightRepository extends JpaRepository<Weight, Long> {
    Optional<Weight> findByUserAndDate(User user, LocalDate date);
}
