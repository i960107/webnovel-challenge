package com.naver.webnovel.user.dto;

import com.naver.webnovel.base.ValidationRegex;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserLoginRequest {
    @Pattern(regexp = ValidationRegex.regexId, message = ValidationRegex.regexIdMessage)
    private final String id;

    @Pattern(regexp = ValidationRegex.regexPw, message = ValidationRegex.regexPwMessage)
    private final String password;
}
