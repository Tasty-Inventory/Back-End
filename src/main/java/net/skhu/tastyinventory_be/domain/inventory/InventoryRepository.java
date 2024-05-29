package net.skhu.tastyinventory_be.domain.inventory;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    List<Inventory> findAllByNameContaining(String name);
}
