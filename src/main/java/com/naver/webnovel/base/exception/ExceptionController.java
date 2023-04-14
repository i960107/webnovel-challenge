package com.naver.webnovel.base.exception;

import com.naver.webnovel.base.BaseExceptionResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler
    public @ResponseBody
    BaseExceptionResponse handBaseException(BaseException exception) {
        return new BaseExceptionResponse<>(exception.getStatus());
    }
}
