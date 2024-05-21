package net.skhu.tastyinventory_be.service.Menu;

import lombok.RequiredArgsConstructor;
import net.skhu.tastyinventory_be.domain.menu.Menu;
import net.skhu.tastyinventory_be.domain.menu.MenuRepository;
import net.skhu.tastyinventory_be.dto.MenuSaveRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;

    public Menu save(MenuSaveRequestDto requestDto){
        return menuRepository.save(requestDto.toEntity());
    }
}
