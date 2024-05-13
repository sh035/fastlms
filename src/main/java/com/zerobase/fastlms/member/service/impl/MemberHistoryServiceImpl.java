package com.zerobase.fastlms.member.service.impl;

import com.zerobase.fastlms.member.entity.MemberHistory;
import com.zerobase.fastlms.member.model.LoginHistoryDto;
import com.zerobase.fastlms.member.repository.MemberHistoryRepository;
import com.zerobase.fastlms.member.service.MemberHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberHistoryServiceImpl implements MemberHistoryService {

    private final MemberHistoryRepository memberHistoryRepository;

    @Override
    public void saveLoginHistory(MemberHistory memberHistory) {
        memberHistoryRepository.save(memberHistory);

    }
    @Override
    public List<LoginHistoryDto> getMemberHistoryList(String userId) {
        List<MemberHistory> loginHistories =
                memberHistoryRepository.findLoginHistoriesByUserIdOrderByLoginDateAsc(userId);

        return loginHistories.stream()
                .map(LoginHistoryDto::from)
                .collect(Collectors.toList());
    }

}
