package net.skhu.tastyinventory_be.dto.menu;

import lombok.Builder;

public record MenuUpdateRequestDto(
        String name
) {
    @Builder
    public MenuUpdateRequestDto(String name) {
        this.name = name;
    }

}
