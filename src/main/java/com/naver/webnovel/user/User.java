package com.naver.webnovel.user;

import com.naver.webnovel.base.BaseTimeEntity;
import com.naver.webnovel.base.Gender;
import com.naver.webnovel.base.Status;
import com.naver.webnovel.base.ValidationRegex;
import com.naver.webnovel.purchase.keep.Keep;
import com.naver.webnovel.purchase.keep_ticket.KeepTicketPurchase;
import com.naver.webnovel.user_view.View;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Entity
@Getter
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @NotBlank
    @Pattern(regexp = ValidationRegex.regexId)
    @Column(name = "id", length = 20, nullable = false)
    private String id;

    @NotBlank
    @Pattern(regexp = ValidationRegex.regexPw)
    @Column(name = "password", length = 100, nullable = false)
    private String password;

    @NotBlank
    @Pattern(regexp = ValidationRegex.regexNickName)
    @Column(name = "nickname", length = 10, nullable = false)
    private String nickname;

    @NotBlank
    @Length(max = 30)
    @Column(name = "name", length = 30, nullable = false)
    private String name;

    @NotBlank
    @Pattern(regexp = ValidationRegex.regexPhone)
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

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<View> views;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Keep> keeps;

    @PositiveOrZero
    @Column
    private Long cash;

    @PositiveOrZero
    @Column
    private Long keepTicket;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<KeepTicketPurchase> ticketPurchases;

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
        this.views = new ArrayList<>();
        this.cash = 0L;
        this.keepTicket = 0L;
    }
}
