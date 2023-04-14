package com.naver.webnovel.user.dto;

import com.naver.webnovel.user.User;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserCreateResponse {
    private Long idx;
    private String id;
    private String nickname;
    private String name;
    private String phone;
    private LocalDate birthDate;
    private String gender;
    private Long cash;
    private Long keepTicket;
    private String status;
    private LocalDateTime createdAt;

    @Builder
    private UserCreateResponse(Long idx, String id, String nickname, String name, String phone,
                               LocalDate birthDate, String gender, Long cash, Long keepTicket, LocalDateTime createdAt,
                               String status) {
        this.idx = idx;
        this.id = id;
        this.nickname = nickname;
        this.name = name;
        this.phone = phone;
        this.birthDate = birthDate;
        this.gender = gender;
        this.cash = cash;
        this.keepTicket = keepTicket;
        this.createdAt = createdAt;
        this.status = status;
    }

    public static UserCreateResponse fromEntity(User user) {
        return UserCreateResponse.builder()
                .idx(user.getIdx())
                .id(user.getId())
                .name(user.getName())
                .phone(user.getPhone())
                .birthDate(user.getBirthDate())
                .gender(user.getGender().name())
                .createdAt(user.getCreatedAt())
                .cash(user.getCash())
                .keepTicket(user.getKeepTicket())
                .status(user.getStatus().name())
                .build();

    }

}
