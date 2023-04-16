package com.naver.webnovel.novel.novel.dto;

import com.naver.webnovel.novel.novel.Novel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class CreateNovelResponse {
    private final Long novelIdx;
    private final String author;
    private final String title;
    private final String thumbnail;
    private final String description;
    private final Long episodeCount;
    private final String status;

    public static CreateNovelResponse fromEntity(final Novel novel) {
        return CreateNovelResponse.builder()
                .novelIdx(novel.getIdx())
                .author(novel.getAuthor().getPenname())
                .title(novel.getTitle())
                .thumbnail(novel.getThumbnail())
                .description(novel.getDescription())
                .episodeCount(novel.getEpisodeCount())
                .status(novel.getStatus().name())
                .build();
    }
}
