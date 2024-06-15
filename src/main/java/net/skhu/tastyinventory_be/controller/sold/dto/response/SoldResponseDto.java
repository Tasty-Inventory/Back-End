package net.skhu.tastyinventory_be.controller.sold.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SoldResponseDto {
    private Long id;
    private String menuName;
    private Long count;

    public static SoldResponseDto of(Long id, String menuName, Long count) {
        return new SoldResponseDto(id, menuName, count);
    }
}
