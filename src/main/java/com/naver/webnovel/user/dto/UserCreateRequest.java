package com.naver.webnovel.user.dto;

import com.naver.webnovel.base.Gender;
import com.naver.webnovel.base.ValidationRegex;
import com.naver.webnovel.user.User;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@RequiredArgsConstructor
public class UserCreateRequest {
    @NotBlank
    @Pattern(regexp = ValidationRegex.regexId, message = "숫자, 영문자 소문자, 8 ~ 12자리")
    private final String id;

    @NotBlank
    @Pattern(regexp = ValidationRegex.regexPw, message = "SHA256으로 암호화 필요")
    private String password;

    @NotBlank
    @Pattern(regexp = ValidationRegex.regexNickName, message = "숫자, 영문자 소문자, _, 4~ 10자리")
    private String nickname;

    @NotBlank
    @Length(max = 30)
    private String name;

    @NotBlank
    @Length(max = 15)
    private String phone;

    @NotNull
    @Column(name = "birthdate", nullable = false)
    private LocalDate birthDate;

    @NotNull
    private String gender;

    public User toEntity() {
        return User.builder()
                .id(id)
                .password(password)
                .nickname(nickname)
                .name(name)
                .phone(phone)
                .birthDate(birthDate)
                .gender(Gender.valueOf(gender))
                .build();
    }
}
