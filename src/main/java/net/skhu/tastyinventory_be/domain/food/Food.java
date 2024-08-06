package net.skhu.tastyinventory_be.domain.food;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.skhu.tastyinventory_be.domain.BaseEntity;
import net.skhu.tastyinventory_be.domain.user.User;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "FOOD")
@Entity
public class Food extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String meal;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer carbohydrate;

    @Column(nullable = false)
    private Integer protein;

    @Column(nullable = false)
    private Integer fat;

    @Builder
    public Food(User user, LocalDate date, String meal, String name, Integer carbohydrate, Integer protein, Integer fat) {
        this.user = user;
        this.date = date;
        this.meal = meal;
        this.name = name;
        this.carbohydrate = carbohydrate;
        this.protein = protein;
        this.fat = fat;
    }
}
