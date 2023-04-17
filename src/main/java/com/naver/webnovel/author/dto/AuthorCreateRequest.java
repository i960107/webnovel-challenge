package com.naver.webnovel.author.dto;

import com.naver.webnovel.author.Author;
import com.naver.webnovel.user.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AuthorCreateRequest {
    private final Long userIdx;
    private final String penname;

    public Author toEntity(User user) {
        return Author.builder()
                .user(user)
                .penname(penname)
                .build();
    }
}
