package net.skhu.tastyinventory_be.domain.menu;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.skhu.tastyinventory_be.domain.BaseEntity;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "MENU")
@Entity
public class Menu extends BaseEntity {
    @Column(nullable = false, length = 50)
    private String name;

    private String imageUrl;

    @Builder
    public Menu(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }
}
