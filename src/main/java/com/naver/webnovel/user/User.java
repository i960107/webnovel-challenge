package com.naver.webnovel.user;

import com.naver.webnovel.base.BaseTimeEntity;
import com.naver.webnovel.base.Gender;
import com.naver.webnovel.base.Status;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;

@Entity
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @NotNull
    @Length(max = 20)
    @Column(name = "id", length = 20, nullable = false)
    private String id;

    @NotNull
    @Length(max = 100)
    @Column(name = "password", length = 100, nullable = false)
    private String password;

    @NotNull
    @Length(max = 10)
    @Column(name = "nickname", length = 10, nullable = false)
    private String nickname;

    @NotNull
    @Length(max = 30)
    @Column(name = "name", length = 30, nullable = false)
    private String name;

    @NotNull
    @Length(max = 15)
    @Column(name = "phone", length = 15, nullable = false)
    private String phone;

    @NotNull
    @Column(name = "birthdate", nullable = false)
    private LocalDate birthDate;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @Builder
    public User(final String id, final String password, final String nickname, final String name, final String phone,
                final LocalDate birthDate, Gender gender) {
        this.id = id;
        this.password = password;
        this.nickname = nickname;
        this.name = name;
        this.phone = phone;
        this.birthDate = birthDate;
        this.gender = gender;
        this.status = Status.ACTIVATED;
    }
}
