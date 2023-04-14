package com.naver.webnovel.base.exception;

import lombok.Getter;

@Getter
public enum BaseResponseStatus {
    // 2000 : 요청 성공
    SUCCESS(true, 2000, "요청에 성공하였습니다.."),

    // 4000 : 클라이언트 오류
    BAD_REQUEST(false, 4000, "입력값을 확인해주세요."),
    // jwt오류
    EMPTY_TOKEN(false, 4001, "토큰을 입력해주세요."),
    INVALID_TOKEN(false, 4002, "유효하지 않는 JWT입니다."),
    EXPIRED_TOKEN(false, 4003, "만료된 JWT입니다."),
    UNAUTHORIZED(false, 4004, "권한이 없는 유저입니다"),

    // 5000 : 서버 오류
    RESPONSE_ERROR(false, 5000, "서버 에러가 발생하였습니다.");
    private final boolean isSuccess;
    private final int code;
    private final String message;

    BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
