package com.naver.webnovel.base.exception;

import com.naver.webnovel.base.BaseExceptionResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = {BaseException.class})
    public ResponseEntity<BaseExceptionResponse> handleBaseException(BaseException exception) {
        int code = exception.getStatus().getCode();
        HttpStatus status;
        if (code >= 4001 && code <= 4010) {
            status = HttpStatus.UNAUTHORIZED;
        } else if (code >= 4000 && code < 5000) {
            status = HttpStatus.BAD_REQUEST;
        } else {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(new BaseExceptionResponse(exception.getStatus()), status);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<BaseExceptionResponse> handleMethodValidException(MethodArgumentNotValidException e) {
        BaseExceptionResponse response = null;

        Map<String, String> message = new HashMap<>();
        List<FieldError> errors = e.getBindingResult().getFieldErrors();
        for (FieldError error : errors) {
            message.put(error.getField(), error.getDefaultMessage());
        }
        response = new BaseExceptionResponse(message);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
