package net.skhu.tastyinventory_be.domain.weight;

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
@Table(name = "WEIGHT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Weight extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private Integer weight;

    @Builder
    public Weight(User user, LocalDate date, Integer weight) {
        this.user = user;
        this.date = date;
        this.weight = weight;
    }
}
