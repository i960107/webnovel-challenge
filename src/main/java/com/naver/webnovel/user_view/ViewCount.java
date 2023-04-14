package com.naver.webnovel.user_view;

import com.naver.webnovel.base.BaseTimeEntity;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class ViewCount extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @NotNull
    @Column
    private LocalDate date;

    @NotNull
    @Column
    private Long novel_idx;

    @NotNull
    @Column
    private Long count;

    public ViewCount(LocalDate date, Long novel_idx, Long count) {
        this.date = date;
        this.novel_idx = novel_idx;
        this.count = count;
    }
}
