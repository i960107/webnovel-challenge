package com.naver.webnovel.novel.novel_page;

import com.naver.webnovel.base.BaseTimeEntity;
import com.naver.webnovel.base.Status;
import com.naver.webnovel.novel.novel_episode.NovelEpisode;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

public class NovelPage extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "episode_idx", nullable = false)
    private NovelEpisode episode;

    @Column(name = "sequence")
    @Min(value = 1)
    private Long sequence;

    @NotBlank
    @Length(max = 1000)
    @Column(name = "content", length = 1000)
    private String content;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @Builder
    public NovelPage(NovelEpisode episode, Long sequence, String content) {
        this.episode = episode;
        this.sequence = sequence;
        this.content = content;
        this.status = Status.ACTIVATED;
    }
}
