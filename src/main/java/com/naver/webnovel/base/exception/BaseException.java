package com.naver.webnovel.base.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseException extends Exception {
    private BaseResponseStatus status;
}
