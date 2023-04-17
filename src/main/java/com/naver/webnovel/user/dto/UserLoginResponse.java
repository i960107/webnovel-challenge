package com.naver.webnovel.user.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserLoginResponse {
    private final Long userIdx;
    private final String userAccessToken;
}
