package net.skhu.tastyinventory_be.domain.sold;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.skhu.tastyinventory_be.domain.BaseEntity;
import net.skhu.tastyinventory_be.domain.menu.Menu;

import java.time.LocalDate;

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
}
