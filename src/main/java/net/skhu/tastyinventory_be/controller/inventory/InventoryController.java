package net.skhu.tastyinventory_be.controller.inventory;

import lombok.RequiredArgsConstructor;

import net.skhu.tastyinventory_be.domain.inventory.Inventory;
import net.skhu.tastyinventory_be.dto.InventorySaveRequestDto;
import net.skhu.tastyinventory_be.service.Inventory.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/inventory")
@RestController
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Long save(@RequestBody InventorySaveRequestDto requestDto){
        return inventoryService.save(requestDto);   // inventoryService 객체 -> requestDto 포함된 데이터 저장 메서드 호출, 데이터 식별자 반환
    }
    @GetMapping
    public List<Inventory> getAllInventory(){
        return inventoryService.findAll();
    }

}
