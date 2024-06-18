package net.skhu.tastyinventory_be.service;

import lombok.RequiredArgsConstructor;
import net.skhu.tastyinventory_be.controller.sold.dto.request.SoldMenuDto;
import net.skhu.tastyinventory_be.controller.sold.dto.request.SoldRequestDto;
import net.skhu.tastyinventory_be.controller.sold.dto.response.SoldResponseDto;
import net.skhu.tastyinventory_be.domain.menu.Menu;
import net.skhu.tastyinventory_be.domain.menu.MenuRepository;
import net.skhu.tastyinventory_be.domain.sold.Sold;
import net.skhu.tastyinventory_be.domain.sold.SoldRepository;
import net.skhu.tastyinventory_be.exception.ErrorCode;
import net.skhu.tastyinventory_be.exception.model.NotFoundException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class SoldService {
    private final SoldRepository soldRepository;
    private final MenuRepository menuRepository;

    public List<SoldResponseDto> findAllSold() {
        List<Menu> menuList = menuRepository.findAll();
        return menuList.stream()
                .map(menu -> {
                    Sold sold = soldRepository.findByMenuId(menu.getId())
                            .stream()
                            .findFirst()
                            .orElse(null);
                    Long soldCount = sold != null ? sold.getCount() : 0L;
                    return new SoldResponseDto(menu.getId(), menu.getName(), soldCount);
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateAllSold(SoldRequestDto requestDto) {
        LocalDate currentDate = LocalDate.now();
        for (SoldMenuDto soldMenuDto : requestDto.getSoldMenuList()) {
            Menu menu = menuRepository.findById(soldMenuDto.getMenuId()).orElseThrow(
                    () -> new NotFoundException(
                            ErrorCode.NOT_FOUND_MENU_EXCEPTION,
                            ErrorCode.NOT_FOUND_MENU_EXCEPTION.getMessage() + soldMenuDto.getMenuId()));

            List<Sold> soldList = soldRepository.findByMenuId(menu.getId());
            Sold sold;
            if (soldList.isEmpty()) {
                sold = Sold.builder()
                        .date(currentDate)
                        .menu(menu)
                        .count(soldMenuDto.getSoldCount())
                        .build();
            } else {
                sold = soldList.get(0);
                sold.update(soldMenuDto.getSoldCount());
            }
            soldRepository.save(sold);
        }
    }

    @Scheduled(cron = "0 0 0 * * *")
    @Transactional
    public void resetSoldCounts() {
        List<Sold> soldList = soldRepository.findAll();
        for (Sold sold : soldList) {
            sold.setCount(0L);
        }
        soldRepository.saveAll(soldList);
    }
}
