package com.naver.webnovel.novel.novel.dto;

import com.naver.webnovel.author.Author;
import com.naver.webnovel.novel.novel.Novel;
import java.util.Optional;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@Builder
@RequiredArgsConstructor
public class CreateNovelRequest {
    @PositiveOrZero
    private final Long authorIdx;
    @NotBlank
    @Length(max = 100)
    private final String title;
    private final Optional<String> thumbnail;
    private final Optional<String> description;

    public Novel toEntity(Author author) {
        return Novel.builder()
                .author(author)
                .title(title)
                .thumbnail(thumbnail)
                .description(description)
                .build();
    }
}
