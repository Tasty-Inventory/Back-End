package net.skhu.tastyinventory_be.controller.menu;

import lombok.RequiredArgsConstructor;
import net.skhu.tastyinventory_be.controller.menu.dto.MenuResponseDto;
import net.skhu.tastyinventory_be.controller.menu.dto.MenuSaveRequestDto;
import net.skhu.tastyinventory_be.controller.menu.dto.MenuUpdateRequestDto;
import net.skhu.tastyinventory_be.service.MenuService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MenuController {

    private final MenuService menuService;

    @PostMapping("menus")
    public void save(@RequestBody MenuSaveRequestDto requestDto) {
        menuService.saveMenu(requestDto);
    }

    @GetMapping("menus/{id}")
    public MenuResponseDto findMenuById(@PathVariable Long id) {
        return menuService.findMenuById(id);
    }

    @GetMapping("menus")
    public List<MenuResponseDto> findAllMenu() {
        return menuService.findAll();
    }

    @PatchMapping("menus/{id}")
    public void updateMenuById(@PathVariable Long id, @RequestBody MenuUpdateRequestDto requestDto) {
        menuService.updateMenuById(id, requestDto);
    }

    @DeleteMapping("menus/{id}")
    public void deleteMenuById(@PathVariable Long id) {
        menuService.deleteMenuById(id);
    }

}
