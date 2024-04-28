package net.skhu.tastyinventory_be.domain.inventory;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
//    Inventory findByName(String name);
    List<Inventory> findByNameContaining(String name);

}
