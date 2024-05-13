package com.zerobase.fastlms.member.service;

import com.zerobase.fastlms.member.entity.MemberHistory;
import com.zerobase.fastlms.member.model.LoginHistoryDto;

import java.util.List;

public interface MemberHistoryService {
    /**
     * 회원 히스토리 저장
     */
    void saveLoginHistory(MemberHistory memberHistory);

    /**
     * 회원 히스토리 리스트 조회
     */
    List<LoginHistoryDto> getMemberHistoryList(String userId);

}
