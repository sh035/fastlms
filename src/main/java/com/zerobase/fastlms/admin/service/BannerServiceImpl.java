package com.zerobase.fastlms.admin.service;

import com.zerobase.fastlms.admin.dto.BannerDto;
import com.zerobase.fastlms.admin.entity.Banner;
import com.zerobase.fastlms.admin.mapper.BannerMapper;
import com.zerobase.fastlms.admin.model.BannerParam;
import com.zerobase.fastlms.admin.repository.BannerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BannerServiceImpl implements BannerService{

    private final BannerRepository bannerRepository;
    private final BannerMapper bannerMapper;
    @Override
    public boolean add(BannerDto dto) {
        Banner banner = Banner.builder()
                .name(dto.getName())
                .url(dto.getUrl())
                .sortVal(dto.getSortVal())
                .openWay(dto.getOpenWay())
                .usingYn(dto.isUsingYn())
                .fileName(dto.getFileName())
                .urlFileName(dto.getUrlFileName())
                .createAt(LocalDateTime.now())
                .build();
        bannerRepository.save(banner);

        return true;
    }

    @Override
    public BannerDto findById(long id) {
        Banner banner = bannerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 배너입니다."));


        return BannerDto.from(banner);
    }

    @Override
    public boolean update(BannerDto dto) {
        Optional<Banner> optionalBanner = bannerRepository.findById(dto.getId());

        if (!optionalBanner.isPresent()) {
            return false;
        }

        Banner banner = optionalBanner.get();
        banner.setName(dto.getName());
        banner.setUrl(dto.getUrl());
        banner.setSortVal(dto.getSortVal());
        banner.setOpenWay(dto.getOpenWay());
        banner.setUsingYn(dto.isUsingYn());
        banner.setFileName(dto.getFileName());
        banner.setUrlFileName(dto.getUrlFileName());
        bannerRepository.save(banner);

        return true;
    }

    @Override
    public boolean delete(String delIds) {
        if (delIds != null && !delIds.isEmpty()) {
            String[] ids = delIds.split(",");
            for (String x : ids) {
                long id = Long.parseLong(x);
                bannerRepository.deleteById(id);
            }
        }
        return true;
    }

    @Override
    public List<BannerDto> list(BannerParam param) {

        long totalCnt = bannerMapper.selectListCount(param);

        List<BannerDto> banners = bannerMapper.selectList(param);
        if (!CollectionUtils.isEmpty(banners)) {
            int i = 0;
            for (BannerDto x : banners) {
                x.setTotalCnt(totalCnt);
                x.setSeq(totalCnt - param.getPageStart() - i);
                i++;
            }
        }

        return banners;
    }
    @Override
    public List<BannerDto> frontList() {
        List<Banner> banners = bannerRepository.findAllByUsingYnIsTrue();
        return banners.stream()
                .map(BannerDto::from)
                .collect(Collectors.toList());
    }
}
