package net.skhu.tastyinventory_be.service.Inventory;

import jakarta.transaction.Transactional;
import net.skhu.tastyinventory_be.domain.inventory.InventoryRepository;
import net.skhu.tastyinventory_be.controller.inventory.dto.InventoryRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// 엔티티를 처리하는 비즈니스 로직, ex 재고 업데이트, 평균 사용량 계산 등
@Service
public class InventoryService {
    private InventoryRepository inventoryRepository;

    @Autowired
    public InventoryService(InventoryRepository inventoryRepository){
        this.inventoryRepository = inventoryRepository;
    }

    @Transactional
    public Long save(InventoryRequestDto requestDto){
        return inventoryRepository.save(requestDto.toEntity()).getId();
    }
}
