package com.zerobase.fastlms.member.repository;

import com.zerobase.fastlms.member.entity.MemberHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberHistoryRepository extends JpaRepository<MemberHistory, Long> {

    List<MemberHistory> findLoginHistoriesByUserIdOrderByLoginDateAsc(String userId);
}
