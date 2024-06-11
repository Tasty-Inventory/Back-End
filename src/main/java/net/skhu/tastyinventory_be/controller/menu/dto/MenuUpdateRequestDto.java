package net.skhu.tastyinventory_be.controller.menu.dto;

import lombok.Builder;

public record MenuUpdateRequestDto(
        String name
) {
    @Builder
    public MenuUpdateRequestDto(String name) {
        this.name = name;
    }

}
