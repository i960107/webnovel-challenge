package com.naver.webnovel.base;

public enum Status {
    DELETED(0),
    ACTIVATED(1);

    private final int code;

    Status(int code) {
        this.code = code;
    }
}
