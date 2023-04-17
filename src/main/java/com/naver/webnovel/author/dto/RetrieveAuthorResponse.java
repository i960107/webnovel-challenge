package com.naver.webnovel.author.dto;

import com.naver.webnovel.author.Author;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RetrieveAuthorResponse {
    private final Long authorIdx;
    private final Long userIdx;
    private final String penname;
    private final String status;

    public static RetrieveAuthorResponse fromEntity(final Author author) {
        return RetrieveAuthorResponse.builder()
                .authorIdx(author.getIdx())
                .userIdx(author.getUser().getIdx())
                .penname(author.getPenname())
                .status(author.getStatus().name())
                .build();
    }
}
