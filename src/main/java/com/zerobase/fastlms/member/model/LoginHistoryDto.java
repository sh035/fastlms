package com.zerobase.fastlms.member.model;

import com.zerobase.fastlms.member.entity.MemberHistory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginHistoryDto {

    private long id;
    private String userId;
    private LocalDateTime loginDate;
    private String ip;
    private String userAgent;

    public static LoginHistoryDto from(MemberHistory history) {
        return LoginHistoryDto.builder()
                .id(history.getId())
                .userId(history.getUserId())
                .loginDate(history.getLoginDate())
                .ip(history.getIp())
                .userAgent(history.getUserAgent())
                .build();
    }
}
