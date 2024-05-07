package net.skhu.tastyinventory_be.domain.inventoryVolume;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryVolumeRepository extends JpaRepository<InventoryVolume, Long> {
}
