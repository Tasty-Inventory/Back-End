package net.skhu.tastyinventory_be.service;

import lombok.RequiredArgsConstructor;
import net.skhu.tastyinventory_be.controller.menu.dto.MenuResponseDto;
import net.skhu.tastyinventory_be.controller.menu.dto.MenuSaveRequestDto;
import net.skhu.tastyinventory_be.controller.menu.dto.MenuUpdateRequestDto;
import net.skhu.tastyinventory_be.domain.menu.Menu;
import net.skhu.tastyinventory_be.domain.menu.MenuRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MenuService {
    private final MenuRepository menuRepository;

    @Transactional
    public Menu saveMenu(MenuSaveRequestDto requestDto) {
        Menu menu = Menu.builder()
                .name(requestDto.getName())
                .price(requestDto.getPrice())
                .build();

        return menuRepository.save(menu);
    }

    public MenuResponseDto findMenuById(Long id) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당하는 메뉴가 없습니다. id = " + id));

        return MenuResponseDto.of(menu.getId(), menu.getName(), menu.getPrice());
    }

    public List<MenuResponseDto> findAll() {
        return menuRepository.findAll().stream()
                .map(menu -> MenuResponseDto.of(
                        menu.getId(),
                        menu.getName(),
                        menu.getPrice()
                ))
                .collect(Collectors.toList());
    }

    /*
     * 수정을 해도 참조하는 객체를 변경하지 않는 방식으로 설계.
     */
    public Menu updateMenuById(Long id, MenuUpdateRequestDto requestDto) {
        // 해당 id를 가지는 객체를 반환
        Menu menu = menuRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당하는 메뉴가 없습니다. id = " + id));

        // 해당 객체의 내용을 수정
        menu.update(requestDto.getName(), requestDto.getPrice());

        // 변경된 내용을 DB에 반영
        return menuRepository.save(menu);
    }

    public void deleteMenuById(Long id) {
        menuRepository.deleteById(id);
    }
}
