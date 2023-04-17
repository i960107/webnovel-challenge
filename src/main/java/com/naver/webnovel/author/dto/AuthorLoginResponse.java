package com.naver.webnovel.author.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AuthorLoginResponse {
    private final Long authorIdx;
    private final String authorAccessToken;
}
