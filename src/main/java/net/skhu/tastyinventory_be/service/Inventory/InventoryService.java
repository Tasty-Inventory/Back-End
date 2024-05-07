package net.skhu.tastyinventory_be.service.Inventory;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.skhu.tastyinventory_be.domain.inventory.Inventory;
import net.skhu.tastyinventory_be.domain.inventory.InventoryRepository;
import net.skhu.tastyinventory_be.dto.InventoryResponseDto;
import net.skhu.tastyinventory_be.dto.InventorySaveRequestDto;
import net.skhu.tastyinventory_be.dto.InventoryUpdateRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    @Transactional
    public Long save(InventorySaveRequestDto requestDto){
        return inventoryRepository.save(requestDto.toEntity()).getId();
    }

    public List<Inventory> findAll(){
        return inventoryRepository.findAll();
    }

    public Inventory findById(Long inventoryId) {
        return inventoryRepository.findById(inventoryId).orElseThrow(() ->
                new EntityNotFoundException("Inventory not found with id: " + inventoryId));
    }

    public List<Inventory> searchByName(String searchText) {
        return inventoryRepository.findByNameContaining(searchText);
    }
    @Transactional
    public void update(Long id, InventoryUpdateRequestDto requestDto) {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Inventory not found with id: " + id));
        inventory.update(requestDto.getName(), requestDto.getUnit(), requestDto.getImageUrl());
    }

    @Transactional
    public void delete(Long id) {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Inventory not found with id: " + id));
        inventoryRepository.delete(inventory);
    }


}
