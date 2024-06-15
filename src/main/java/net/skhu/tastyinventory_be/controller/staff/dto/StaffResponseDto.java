package net.skhu.tastyinventory_be.controller.staff.dto;

import lombok.*;
import net.skhu.tastyinventory_be.domain.staff.Staff;


@Builder
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class StaffResponseDto {
    int id;


    String name;

    String phoneNumber;

    String position;

    public static StaffResponseDto of(Staff staff) {
        return StaffResponseDto.builder()
                .id(staff.getId())
                .name(staff.getName())
                .phoneNumber(staff.getPhoneNumber())
                .position(staff.getPosition())
                .build();
    }

}
