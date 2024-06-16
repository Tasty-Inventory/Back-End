package net.skhu.tastyinventory_be.service;

import lombok.RequiredArgsConstructor;
import net.skhu.tastyinventory_be.controller.inventoryRecord.dto.request.InventoryRecordRequestDto;
import net.skhu.tastyinventory_be.controller.inventoryRecord.dto.request.InventoryRecordUpdateRequestDto;
import net.skhu.tastyinventory_be.controller.inventoryRecord.dto.response.InventoryRecordResponseDto;
import net.skhu.tastyinventory_be.domain.inventory.Inventory;
import net.skhu.tastyinventory_be.domain.inventory.InventoryRepository;
import net.skhu.tastyinventory_be.domain.inventoryRecord.InventoryRecord;
import net.skhu.tastyinventory_be.domain.inventoryRecord.InventoryRecordRepository;
import net.skhu.tastyinventory_be.exception.ErrorCode;
import net.skhu.tastyinventory_be.exception.model.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class InventoryRecordService {
    private final InventoryRecordRepository inventoryRecordRepository;
    private final InventoryRepository inventoryRepository;

    @Transactional
    public void createInventoryRecord(InventoryRecordRequestDto requestDto) {
        for (int i = 0; i < requestDto.getInventoryRecords().size(); i++) {
            Long inventoryId = requestDto.getInventoryRecords().get(i).getInventoryId();
            Long currentVolume = requestDto.getInventoryRecords().get(i).getCurrentVolume();
            Long orderVolume = requestDto.getInventoryRecords().get(i).getOrderVolume();

            Inventory inventory = inventoryRepository.findById(inventoryId).orElseThrow(
                    () -> new NotFoundException(
                            ErrorCode.NOT_FOUND_INVENTORY_EXCEPTION,
                            ErrorCode.NOT_FOUND_INVENTORY_EXCEPTION.getMessage()
                    ));

            InventoryRecord inventoryRecord = InventoryRecord.builder()
                    .date(LocalDate.parse(requestDto.getDate()))
                    .inventory(inventory)
                    .currentVolume(currentVolume)
                    .orderVolume(orderVolume)
                    .build();

            inventoryRecordRepository.save(inventoryRecord);
        }
    }
    public List<InventoryRecordResponseDto> getAllInventoryRecords() {
        return inventoryRecordRepository.findAll().stream()
                .map(InventoryRecordResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateInventoryRecord(Long id, InventoryRecordUpdateRequestDto requestDto) {
        InventoryRecord inventoryRecord = inventoryRecordRepository.findById(id).orElseThrow(
                () -> new NotFoundException(
                        ErrorCode.NOT_FOUND_INVENTORY_RECORD_EXCEPTION,
                        ErrorCode.NOT_FOUND_INVENTORY_RECORD_EXCEPTION.getMessage() + id
                ));

        inventoryRecord.update(
                LocalDate.parse(requestDto.getDate()),
                requestDto.getCurrentVolume(),
                requestDto.getOrderVolume()
        );

        inventoryRecordRepository.save(inventoryRecord);
    }

    @Transactional
    public void deleteInventoryRecord(Long id) {
        InventoryRecord inventoryRecord = inventoryRecordRepository.findById(id).orElseThrow(
                () -> new NotFoundException(
                        ErrorCode.NOT_FOUND_INVENTORY_RECORD_EXCEPTION,
                        ErrorCode.NOT_FOUND_INVENTORY_RECORD_EXCEPTION.getMessage() + id
                ));
        inventoryRecordRepository.delete(inventoryRecord);
    }
}
