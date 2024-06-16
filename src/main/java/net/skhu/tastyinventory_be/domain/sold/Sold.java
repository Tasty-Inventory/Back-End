package net.skhu.tastyinventory_be.domain.sold;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.skhu.tastyinventory_be.controller.sold.dto.request.SoldMenuDto;
import net.skhu.tastyinventory_be.controller.sold.dto.request.SoldRequestDto;
import net.skhu.tastyinventory_be.domain.BaseEntity;
import net.skhu.tastyinventory_be.domain.menu.Menu;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "SOLD")
@Entity
public class Sold extends BaseEntity {
    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @Column(nullable = false)
    private Long count;

    @Builder
    public Sold(LocalDate date, Menu menu, Long count) {
        this.date = date;
        this.menu = menu;
        this.count = count;
    }
    public void update(Menu menu, Long count) {
        this.menu = menu; // Menu 객체를 업데이트 (필요에 따라)
        this.count = count; // 판매량을 업데이트
    }

}
