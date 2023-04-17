package com.naver.webnovel.user.dto;

import com.naver.webnovel.base.Gender;
import com.naver.webnovel.base.ValidationRegex;
import com.naver.webnovel.user.User;
import java.time.LocalDate;
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
    @Pattern(regexp = ValidationRegex.regexId, message = ValidationRegex.regexIdMessage)
    private final String id;

    @NotBlank
    @Pattern(regexp = ValidationRegex.regexPw, message = ValidationRegex.regexPwMessage)
    private final String password;

    @NotBlank
    @Pattern(regexp = ValidationRegex.regexNickName, message = ValidationRegex.regexNickNameMessage)
    private final String nickname;

    @NotBlank
    @Length(max = 30)
    private final String name;

    @NotBlank
    @Pattern(regexp = ValidationRegex.regexPhone, message = ValidationRegex.regexPhoneMessage)
    private final String phone;

    @NotNull
    private final LocalDate birthDate;

    @NotNull
    @Pattern(regexp = ValidationRegex.regexGender, message = ValidationRegex.regexGenderMessage)
    private final String gender;

    public User toEntity() {
        return User.builder()
                .id(id)
                .password(password)
                .nickname(nickname)
                .name(name)
                .phone(phone)
                .birthDate(birthDate)
                .gender(Enum.valueOf(Gender.class, gender))
                .build();
    }
}
