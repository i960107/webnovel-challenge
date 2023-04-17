package com.naver.webnovel.base.exception;

import lombok.Getter;

@Getter
public enum BaseResponseStatus {
    // 2000 : 요청 성공
    SUCCESS(true, 2000, "요청에 성공하였습니다.."),

    //4000번대: 클라이언트 오류
    BAD_REQUEST(false, 4000, "입력값을 확인해주세요."),

    // authorization & authentication error(4001 ~ 4010)
    EMPTY_TOKEN(false, 4001, "토큰을 입력해주세요."),
    INVALID_TOKEN(false, 4002, "유효하지 않는 JWT입니다."),
    EXPIRED_TOKEN(false, 4003, "만료된 JWT입니다."),
    UNAUTHORIZED(false, 4004, "권한이 없는 유저입니다."),
    AUTHENTICATION_FAIL(false, 4005, "존재하지 않는 유저입니다."),

    // user 관련 오류(4011~4020)
    DUPLICATE_ID(false, 4010, "이미 등록된 아이디 입니다."),
    DUPLICATE_PHONE(false, 4011, "이미 등록된 휴대폰 번호 입니다."),
    INVALID_USER(false, 4013, "존재하지 않는 유저입니다."),
    //author 관련 오류(4021~4030)
    INVALID_AUTHOR(false, 4021, "존재하지 않는 작가입니다."),
    ALREADY_CREATED_AUTHOR(false, 4022, "이미 등록된 작가입니다."),

    // 5000 : 서버 오류
    RESPONSE_ERROR(false, 5000, "서버 에러가 발생하였습니다."),

    //authorization & authentication

    FAIL_TO_CREATE_ACCESS_TOKEN(false, 5001, "엑세스 토큰 발급 오류."),
    FAIL_TO_LOAD_JWT_KEY(false, 5002, "시크릿키 로드 실패");

    private final boolean isSuccess;
    private final int code;
    private final String message;

    BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
