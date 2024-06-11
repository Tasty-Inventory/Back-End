package net.skhu.tastyinventory_be.service.Menu;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import net.skhu.tastyinventory_be.domain.menu.Menu;
import net.skhu.tastyinventory_be.domain.menu.MenuRepository;

import net.skhu.tastyinventory_be.controller.menu.dto.MenuListResponseDto;
import net.skhu.tastyinventory_be.controller.menu.dto.MenuResponseDto;
import net.skhu.tastyinventory_be.controller.menu.dto.MenuSaveRequestDto;
import net.skhu.tastyinventory_be.controller.menu.dto.MenuUpdateRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;

    @Transactional
    public Menu saveMenu(MenuSaveRequestDto requestDto){
        return menuRepository.save(requestDto.toEntity());
    }

    public MenuListResponseDto findAll(){
        List<Menu> inventories = menuRepository.findAll();
        List<MenuResponseDto> menuResponseDtoList = inventories.stream()
                .map(MenuResponseDto::from)
                .toList();
        return MenuListResponseDto.from(menuResponseDtoList);
    }

    public MenuResponseDto findById(Long menuId) {
        Menu menu = menuRepository.findById(menuId).orElseThrow(() ->
                new EntityNotFoundException("Menu not found with id: " + menuId));
        return MenuResponseDto.from(menu);
    }

    public List<Menu> searchByName(String searchText) {
        return menuRepository.findByNameContaining(searchText);
    }

    @jakarta.transaction.Transactional
    public void update(Long id, MenuUpdateRequestDto requestDto) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Menu not found with id: " + id));
        menu.update(requestDto.name());
    }

    @jakarta.transaction.Transactional
    public void delete(Long id) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Menu not found with id: " + id));
        menuRepository.delete(menu);
    }
}
