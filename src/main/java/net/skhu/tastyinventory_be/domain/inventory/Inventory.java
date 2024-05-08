package net.skhu.tastyinventory_be.domain.inventory;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.skhu.tastyinventory_be.domain.BaseEntity;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "INVENTORY")
@Entity
public class Inventory extends BaseEntity {
    @Column(nullable = false, length = 50)
    private String name;

    @Enumerated(value = EnumType.STRING)
    private Unit unit;

    private String imageUrl;

    @Builder
    public Inventory(String name, Unit unit, String imageUrl) {
        this.name = name;
        this.unit = unit;
        this.imageUrl = imageUrl;
    }
}
