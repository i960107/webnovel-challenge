package com.naver.webnovel.novel.novel;

import com.naver.webnovel.author.Author;
import com.naver.webnovel.base.Status;
import com.naver.webnovel.novel.novel_episode.NovelEpisode;
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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

@Entity
public class Novel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "author_idx", nullable = false)
    private Author author;

    @OneToMany(mappedBy = "novel")
    private List<NovelEpisode> episodes;

    @NotBlank
    @Length(max = 100)
    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @Length(max = 300)
    @Column(name = "thumbnail", length = 300)
    private String thumbnail;

    @Column(name = "episode_count", nullable = false)
    private Long episodeCount;

    @Length(max = 3000)
    @Column(name = "description", length = 3000)
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;


    @Builder
    public Novel(Author author, final String title, Optional<String> thumbnail, Optional<String> description) {
        this.author = author;
        this.title = title;
        thumbnail.ifPresent(url -> this.thumbnail = url);
        this.episodeCount = 0L;
        description.ifPresent(content -> this.description = content);
        this.status = Status.ACTIVATED;
    }


}