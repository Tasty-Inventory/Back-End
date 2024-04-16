package net.skhu.tastyinventory_be.controller.inventory;

import lombok.RequiredArgsConstructor;

import net.skhu.tastyinventory_be.controller.inventory.dto.InventoryDto;
import net.skhu.tastyinventory_be.service.Inventory.InventoryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping("/")
    public Long save(@RequestBody InventoryDto requestDto){
        return inventoryService.save(requestDto);
    }

}
