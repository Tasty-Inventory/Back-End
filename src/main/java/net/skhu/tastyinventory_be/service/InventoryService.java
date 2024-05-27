package net.skhu.tastyinventory_be.service;

import lombok.RequiredArgsConstructor;
import net.skhu.tastyinventory_be.controller.inventory.dto.response.InventoryResponseDto;
import net.skhu.tastyinventory_be.domain.inventory.Inventory;
import net.skhu.tastyinventory_be.domain.inventory.InventoryRepository;
import net.skhu.tastyinventory_be.domain.inventory.Unit;
import net.skhu.tastyinventory_be.exception.ErrorCode;
import net.skhu.tastyinventory_be.exception.model.NotFoundException;
import net.skhu.tastyinventory_be.external.client.aws.S3Service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class InventoryService {
    private final S3Service s3Service;
    private final InventoryRepository inventoryRepository;

    @Transactional
    public void createInventory(String name, Unit unit, MultipartFile image) {
        String imageUrl = s3Service.uploadImage(image, "inventory");

        Inventory inventory = Inventory.builder()
                .name(name)
                .unit(unit)
                .imageUrl(imageUrl)
                .build();

        inventoryRepository.save(inventory);
    }

    public InventoryResponseDto findInventory(Long id) {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_INVENTORY_EXCEPTION, ErrorCode.NOT_FOUND_INVENTORY_EXCEPTION.getMessage()));

        return InventoryResponseDto.from(inventory);
    }

    public List<InventoryResponseDto> findAllInventory() {
        List<Inventory> inventoryList = inventoryRepository.findAll();
        return inventoryList.stream().map(InventoryResponseDto::from).collect(Collectors.toList());
    }
}
