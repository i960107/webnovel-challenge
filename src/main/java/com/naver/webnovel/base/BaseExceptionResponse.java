package com.naver.webnovel.base;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.naver.webnovel.base.exception.BaseResponseStatus;
import lombok.Getter;

@Getter
@JsonPropertyOrder({"code", "message"})
public class BaseExceptionResponse<T> {
    private final String message;
    private final int code;


    public BaseExceptionResponse(BaseResponseStatus status) {
        this.message = status.getMessage();
        this.code = status.getCode();
    }
}
