package net.skhu.tastyinventory_be.domain.inventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
//    Inventory findByName(String name);
    List<Inventory> findByNameContaining(String name);

}
