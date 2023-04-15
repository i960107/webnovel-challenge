package com.naver.webnovel.base;

public class ValidationRegex {
    public final static String regexNickName = "^[a-z_1-9]{4,10}$";
    public final static String regexNickNameMessage = "숫자, 영문자 소문자, _, 4~ 10자리";

    public final static String regexId = "^[a-z1-9]{8,20}$";
    public final static String regexIdMessage = "숫자, 영문자 소문자, 8 ~ 12자리";

    public final static String regexPw = "^[0-9a-fA-F]{64}$";
    public final static String regexPwMessage = "SHA256으로 암호화 필요";

    public final static String regexPhone = "^[0-9]{11,15}$";
    public final static String regexPhoneMessage = "숫자 11 ~ 15 자리";

    public final static String regexGender = "^(FEMALE|MALE)$";
    public final static String regexGenderMessage = "FEMALE|MALE";

    public final static String regexDate = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$";
    public final static String regexDateMessage = "yyyy-MM-dd";
}
