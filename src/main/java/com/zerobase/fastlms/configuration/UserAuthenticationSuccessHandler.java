package com.zerobase.fastlms.configuration;

import com.zerobase.fastlms.member.entity.MemberHistory;
import com.zerobase.fastlms.member.service.MemberHistoryService;
import com.zerobase.fastlms.member.service.MemberService;
import com.zerobase.fastlms.util.WebUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
public class UserAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final MemberService memberService;
    private final MemberHistoryService memberHistoryService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String userId = authentication.getName();
        String clientIp = WebUtils.getClientIp(request);
        String userAgent = WebUtils.getUserAgent(request);

        MemberHistory memberHistory = MemberHistory.builder()
                .userId(userId)
                .ip(clientIp)
                .userAgent(userAgent)
                .build();
        memberHistoryService.saveLoginHistory(memberHistory);
        memberService.updateLoginDate(userId, LocalDateTime.now());

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
