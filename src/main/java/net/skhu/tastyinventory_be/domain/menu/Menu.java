package net.skhu.tastyinventory_be.domain.menu;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.skhu.tastyinventory_be.domain.BaseEntity;

@Getter
@Table(name = "MENU")
@NoArgsConstructor
@Entity
public class Menu extends BaseEntity {
    @Column(nullable = false)
    private String name;

    private Long price;

    private String image;

    @Builder
    public Menu(String name, Long price, String image) {
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public void update(String name, Long price) {
        this.name = name;
        this.price = price;
    }
}
