package com.naver.webnovel.novel.novel;

import com.naver.webnovel.author.Author;
import com.naver.webnovel.base.Status;
import com.naver.webnovel.novel.novel_episode.NovelEpisode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Entity
@NoArgsConstructor
@Getter
public class Novel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "author_idx", nullable = false)
    private Author author;

    @OneToMany(mappedBy = "novel", fetch = FetchType.LAZY)
    private List<NovelEpisode> episodes = new ArrayList<>();

    @NotBlank
    @Length(max = 100)
    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @Length(max = 300)
    @Column(name = "thumbnail", length = 300)
    private String thumbnail;

    @PositiveOrZero
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
        this.title = title;
        setAuthor(author);
        thumbnail.ifPresent(url -> this.thumbnail = url);
        this.episodeCount = 0L;
        description.ifPresent(content -> this.description = content);
        this.status = Status.ACTIVATED;
    }

    private void setAuthor(Author author) {
        this.author = author;
        author.addNovel(this);
    }

}