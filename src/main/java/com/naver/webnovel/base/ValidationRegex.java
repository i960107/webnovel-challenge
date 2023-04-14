package com.naver.webnovel.base;

public class ValidationRegex {
    public final static String regexNickName = "^[a-z_1-9]{4,10}$";//영문자 소문자,_,숫자 4~10자리
    public final static String regexId = "^[a-z1-9]{8,20}$";//영문자 소문자,_,숫자 4~10자리
    public final static String regexPw = "^[0-9a-fA-F]{64}$";//encrypted by sha256
}
