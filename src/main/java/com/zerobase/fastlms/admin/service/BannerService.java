package com.zerobase.fastlms.admin.service;

import com.zerobase.fastlms.admin.dto.BannerDto;
import com.zerobase.fastlms.admin.model.BannerParam;

import java.util.List;

public interface BannerService {

    boolean add(BannerDto dto);

    BannerDto findById(long id);

    boolean update(BannerDto dto);

    boolean delete(String delIds);

    List<BannerDto> list(BannerParam param);


    List<BannerDto> frontList();
}
