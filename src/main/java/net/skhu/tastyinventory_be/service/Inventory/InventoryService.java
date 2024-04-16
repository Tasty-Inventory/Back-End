package net.skhu.tastyinventory_be.service.Inventory;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.skhu.tastyinventory_be.domain.inventory.InventoryRepository;
import net.skhu.tastyinventory_be.dto.InventorySaveRequestDto;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    @Transactional
    public Long save(InventorySaveRequestDto requestDto){
        return inventoryRepository.save(requestDto.toEntity()).getId();
    }
}
