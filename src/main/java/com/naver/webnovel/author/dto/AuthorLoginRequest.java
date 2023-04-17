package com.naver.webnovel.author.dto;

import com.naver.webnovel.base.ValidationRegex;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AuthorLoginRequest {
    @Positive
    private final Long userIdx;

    @Positive
    private final Long authorIdx;
}
