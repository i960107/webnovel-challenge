package com.naver.webnovel.author.dto;

import com.naver.webnovel.author.Author;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class AuthorCreateResponse {
    private final Long authorIdx;
    private final Long userIdx;
    private final String penname;
    private final LocalDateTime createdAt;
    private final String status;

    public static AuthorCreateResponse fromEntity(Author author) {
        return AuthorCreateResponse.builder()
                .authorIdx(author.getIdx())
                .userIdx(author.getUser().getIdx())
                .penname(author.getPenname())
                .createdAt(author.getCreatedAt())
                .status(author.getStatus().name())
                .build();
    }
}
