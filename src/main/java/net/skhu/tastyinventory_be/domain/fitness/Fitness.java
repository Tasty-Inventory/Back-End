package net.skhu.tastyinventory_be.domain.fitness;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.skhu.tastyinventory_be.domain.BaseEntity;
import net.skhu.tastyinventory_be.domain.user.User;

import java.time.LocalDate;

@Entity
@Getter
@Table(name = "FITNESS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Fitness extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer minute;

    @Column(nullable = false)
    private Integer calories;

    @Builder
    public Fitness(User user, LocalDate date, String name, Integer minute, Integer calories) {
        this.user = user;
        this.date = date;
        this.name = name;
        this.minute = minute;
        this.calories = calories;
    }
}
