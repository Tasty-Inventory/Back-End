package net.skhu.tastyinventory_be.service.inventoryVolume;

import lombok.RequiredArgsConstructor;
import net.skhu.tastyinventory_be.domain.inventoryVolume.InventoryVolume;
import net.skhu.tastyinventory_be.domain.inventoryVolume.InventoryVolumeRepository;
import net.skhu.tastyinventory_be.controller.inventory.dto.InventoryVolumeRequestDto;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class InventoryVolumeService {

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
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이디입니다 id = " + id));    //에러 코드 맞는지 확인
    }

}
