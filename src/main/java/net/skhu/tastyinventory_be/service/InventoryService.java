package net.skhu.tastyinventory_be.service;

import lombok.RequiredArgsConstructor;
import net.skhu.tastyinventory_be.domain.inventory.Inventory;
import net.skhu.tastyinventory_be.domain.inventory.InventoryRepository;
import net.skhu.tastyinventory_be.domain.inventory.Unit;
import net.skhu.tastyinventory_be.external.client.aws.S3Service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
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
}
