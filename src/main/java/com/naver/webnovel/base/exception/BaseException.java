package com.naver.webnovel.base.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class BaseException extends Exception {
    private final BaseResponseStatus status;

    public BaseException(BaseResponseStatus status) {
        this.status = status;
    }
}
