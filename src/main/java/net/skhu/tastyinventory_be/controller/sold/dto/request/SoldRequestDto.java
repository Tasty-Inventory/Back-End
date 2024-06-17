package net.skhu.tastyinventory_be.controller.sold.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SoldRequestDto {
    @NotBlank(message = "날짜를 입력하세요")
    @Pattern(
            regexp="^\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$",
            message = "YYYY-MM-DD 형식으로 입력하세요"
    )
    private String date;

    @NotNull
    private List<SoldMenuDto> soldMenuList;
}
