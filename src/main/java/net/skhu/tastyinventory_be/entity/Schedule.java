package net.skhu.tastyinventory_be.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalDateTime;


@Entity
@Data
@NoArgsConstructor // 파라미터가 없는 디폴트 생성자를 자동으로 생성
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int blockId;

    private LocalDateTime date;

    @Enumerated(value = EnumType.STRING)
    private DayOfWeek dayOfWeek;

    @Builder
    public Schedule(int blockId, LocalDateTime date){
        this.blockId=blockId;
        this.date = date;
        this.dayOfWeek = date.getDayOfWeek();
    }

    public void setBlockId(int blockId) {
        this.blockId = blockId;
    }
}