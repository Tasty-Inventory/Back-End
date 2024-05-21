package net.skhu.tastyinventory_be.service.Inventory;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.skhu.tastyinventory_be.domain.inventory.Inventory;
import net.skhu.tastyinventory_be.domain.inventory.InventoryRepository;
import net.skhu.tastyinventory_be.dto.InventoryListResponseDto;
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
    public Inventory save(InventorySaveRequestDto requestDto){
        return inventoryRepository.save(requestDto.toEntity());
    }

    public InventoryListResponseDto findAll(){
        List<Inventory> inventories = inventoryRepository.findAll();
        List<InventoryResponseDto> inventoryResponseDtoList = inventories.stream()
                .map(InventoryResponseDto::from)
                .toList();
        return InventoryListResponseDto.from(inventoryResponseDtoList);
    }

    public InventoryResponseDto findById(Long inventoryId) {
        Inventory inventory = inventoryRepository.findById(inventoryId).orElseThrow(() ->
                new EntityNotFoundException("Inventory not found with id: " + inventoryId));
        return InventoryResponseDto.from(inventory);
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
