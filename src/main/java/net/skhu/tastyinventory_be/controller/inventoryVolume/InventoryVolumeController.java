package net.skhu.tastyinventory_be.controller.inventoryVolume;

import lombok.RequiredArgsConstructor;
import net.skhu.tastyinventory_be.domain.inventoryVolume.InventoryVolume;
import net.skhu.tastyinventory_be.dto.InventoryVolumeRequestDto;
import net.skhu.tastyinventory_be.service.inventoryVolume.InventoryVolumeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/inventory-volume")
@RestController
public class InventoryVolumeController {

    private final InventoryVolumeService inventoryVolumeService;

    @PostMapping
    public ResponseEntity<?> createInventoryVolume(@RequestBody InventoryVolumeRequestDto requestDto) {
        InventoryVolume inventoryVolume = inventoryVolumeService.create(requestDto);
        return ResponseEntity.ok().body(inventoryVolume);
    }
    @PutMapping("{id}")
    public ResponseEntity<?> updateInventoryVolume(@PathVariable("id") Long id, @RequestBody InventoryVolumeRequestDto requestDto) {
        InventoryVolume inventoryVolume = inventoryVolumeService.update(id, requestDto);
        return ResponseEntity.ok().body(inventoryVolume);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getInventoryVolume(@PathVariable("id") Long id) {
        InventoryVolume inventoryVolume = inventoryVolumeService.findById(id);
        return ResponseEntity.ok().body(inventoryVolume);
    }
}
