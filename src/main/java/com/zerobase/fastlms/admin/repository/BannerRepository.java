package com.zerobase.fastlms.admin.repository;

import com.zerobase.fastlms.admin.entity.Banner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BannerRepository extends JpaRepository<Banner, Long> {
    List<Banner> findAllByOrderBySortValDesc();
    List<Banner> findAllByUsingYnIsTrue();
}
