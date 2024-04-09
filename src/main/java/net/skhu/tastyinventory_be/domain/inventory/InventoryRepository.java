package net.skhu.tastyinventory_be.domain.inventory;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    //각 엔티티에 대한 crud 작업을 수행할 수 있는 인터페이스 정의
}
