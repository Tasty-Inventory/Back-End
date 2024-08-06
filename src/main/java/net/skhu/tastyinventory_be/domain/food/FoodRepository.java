package net.skhu.tastyinventory_be.domain.food;

import net.skhu.tastyinventory_be.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findAllByDateAndUser(LocalDate date, User user);
}
