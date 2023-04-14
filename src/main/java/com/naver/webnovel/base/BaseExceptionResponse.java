package com.naver.webnovel.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.naver.webnovel.base.exception.BaseResponseStatus;
import java.util.Map;
import lombok.Getter;

@Getter
@JsonPropertyOrder({"code", "message", "errors"})
public class BaseExceptionResponse {
    private final String message;
    private final int code;
    @JsonInclude(Include.NON_NULL)
    private Map<String, String> errors;

    public BaseExceptionResponse(BaseResponseStatus status) {
        this.message = status.getMessage();
        this.code = status.getCode();
    }

    public BaseExceptionResponse(Map<String, String> errors) {
        this.message = BaseResponseStatus.BAD_REQUEST.getMessage();
        this.code = BaseResponseStatus.BAD_REQUEST.getCode();
        this.errors = errors;
    }
}
