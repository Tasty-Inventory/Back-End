package net.skhu.tastyinventory_be.service.inventoryVolume;

import lombok.RequiredArgsConstructor;
import net.skhu.tastyinventory_be.domain.inventoryVolume.InventoryVolume;
import net.skhu.tastyinventory_be.domain.inventoryVolume.InventoryVolumeRepository;
import net.skhu.tastyinventory_be.dto.InventoryVolumeRequestDto;
import net.skhu.tastyinventory_be.exception.model.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static net.skhu.tastyinventory_be.exception.ErrorCode.REQUEST_VALIDATION_EXCEPTION;

@RequiredArgsConstructor
@Service
public class InventoryVolumeService {
    
    @Autowired  // 어노테이션 확인
    private final InventoryVolumeRepository inventoryVolumeRepository;

    public InventoryVolume create(InventoryVolumeRequestDto requestDto) {
        InventoryVolume inventoryVolume = new InventoryVolume();
        return inventoryVolume;
    }

    public InventoryVolume update(Long id, InventoryVolumeRequestDto requestDto) {
        InventoryVolume inventoryVolume = findById(id);
        return inventoryVolume;
    }

    public InventoryVolume findById(Long id) {
        return inventoryVolumeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(REQUEST_VALIDATION_EXCEPTION, "Inventory volume not found with id: "));    //에러 코드 맞는지 확인
    }

}
