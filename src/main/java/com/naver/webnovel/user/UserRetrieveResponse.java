package com.naver.webnovel.user;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserRetrieveResponse {
    private Long idx;
    private String id;
    private String nickname;
    private String name;
    private String phone;
    private LocalDate birthDate;
    private String gender;
    private Long cash;
    private Long keepTicket;
    private Long authorIdx;
    private String status;


    public static UserRetrieveResponse fromEntity(User user) {
        return UserRetrieveResponse.builder()
                .idx(user.getIdx())
                .id(user.getId())
                .name(user.getName())
                .nickname(user.getNickname())
                .phone(user.getPhone())
                .birthDate(user.getBirthDate())
                .gender(user.getGender().name())
                .cash(user.getCash())
                .authorIdx(user.getAuthor().getIdx())
                .keepTicket(user.getKeepTicket())
                .status(user.getStatus().name())
                .build();
    }

}
