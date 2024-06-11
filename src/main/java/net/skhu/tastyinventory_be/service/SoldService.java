package net.skhu.tastyinventory_be.service;

import lombok.RequiredArgsConstructor;
import net.skhu.tastyinventory_be.controller.sold.dto.request.SoldRequestDto;
import net.skhu.tastyinventory_be.domain.menu.Menu;
import net.skhu.tastyinventory_be.domain.menu.MenuRepository;
import net.skhu.tastyinventory_be.domain.sold.Sold;
import net.skhu.tastyinventory_be.domain.sold.SoldRepository;
import net.skhu.tastyinventory_be.exception.ErrorCode;
import net.skhu.tastyinventory_be.exception.model.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class SoldService {
    private final SoldRepository soldRepository;
    private final MenuRepository menuRepository;

    @Transactional
    public void registerSold(SoldRequestDto requestDto) {
        for (int i = 0; i < requestDto.getSoldMenuList().size(); i++) {
            Long menuId = requestDto.getSoldMenuList().get(i).getMenuId();
            Long count = requestDto.getSoldMenuList().get(i).getSoldCount();

            Menu menu = menuRepository.findById(menuId).orElseThrow(
                    () -> new NotFoundException(
                            ErrorCode.NOT_FOUND_MENU_EXCEPTION,
                            ErrorCode.NOT_FOUND_MENU_EXCEPTION.getMessage()
                    ));

            Sold sold = Sold.builder()
                    .date(LocalDate.parse(requestDto.getDate()))
                    .menu(menu)
                    .count(count)
                    .build();

            soldRepository.save(sold);
        }
    }
}
