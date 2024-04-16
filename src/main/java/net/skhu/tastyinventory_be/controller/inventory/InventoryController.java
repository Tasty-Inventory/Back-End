package net.skhu.tastyinventory_be.controller.inventory;

import lombok.RequiredArgsConstructor;

import net.skhu.tastyinventory_be.controller.inventory.dto.InventoryRequestDto;
import net.skhu.tastyinventory_be.dto.InventorySaveRequestDto;
import net.skhu.tastyinventory_be.service.Inventory.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping("/")
    public Long save(@RequestBody InventorySaveRequestDto requestDto){
        return inventoryService.save(requestDto);
    }

}
