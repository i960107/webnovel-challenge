package com.naver.webnovel.user_view;

import com.naver.webnovel.base.BaseTimeEntity;
import com.naver.webnovel.base.Status;
import com.naver.webnovel.novel.novel_page.NovelPage;
import com.naver.webnovel.user.User;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import lombok.Builder;

@Entity
public class View extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "page_idx", nullable = false)
    private NovelPage page;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_idx", nullable = false)
    private User user;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @Builder
    public View(NovelPage page, User user) {
        this.page = page;
        this.user = user;
    }
}
