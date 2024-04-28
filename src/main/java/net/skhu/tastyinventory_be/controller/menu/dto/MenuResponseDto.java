package net.skhu.tastyinventory_be.controller.menu.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class MenuResponseDto {

    private Long id;
    private String name;
    private Long price;

    public static MenuResponseDto of(Long id, String name, Long price) {
        return new MenuResponseDto(id, name, price);
    }
}
