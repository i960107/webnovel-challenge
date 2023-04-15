package com.naver.webnovel.novel.novel_episode;

import com.naver.webnovel.base.BaseTimeEntity;
import com.naver.webnovel.base.Status;
import com.naver.webnovel.novel.novel.Novel;
import com.naver.webnovel.novel.novel_page.NovelPage;
import java.util.List;
import java.util.Optional;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

@Entity
public class NovelEpisode extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "novel_idx", nullable = false)
    private Novel novel;

    @OneToMany(mappedBy = "episode")
    private List<NovelPage> pages;

    @Column(name = "sequence")
    @Min(value = 1)
    private Long sequence;

    @Length(max = 300)
    @Column(name = "thumbnail", length = 300)
    private String thumbnail;

    @Length(max = 100)
    @NotBlank
    @Column(name = "title", length = 100)
    private String title;

    @NotNull
    @Column(name = "bytes")
    private Long bytes;

    @NotNull
    @Column(name = "ticket_required")
    private Integer ticketRequired;

    @Column(name = "page_count")
    private Long pageCount;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @Builder
    public NovelEpisode(final Novel novel, Long sequence, Optional<String> thumbnail, final String title,
                        Integer ticketRequired) {
        this.novel = novel;
        this.sequence = sequence;
        thumbnail.ifPresent(x -> this.thumbnail = x);
        this.title = title;
        this.ticketRequired = ticketRequired;
        this.bytes = 0L;
        this.pageCount = 0L;
        this.status = Status.ACTIVATED;
    }
}
