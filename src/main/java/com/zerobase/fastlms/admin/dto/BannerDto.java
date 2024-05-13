package com.zerobase.fastlms.admin.dto;

import com.zerobase.fastlms.admin.entity.Banner;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BannerDto {

    private Long id;
    private String name;
    private String url;
    private int sortVal;
    private int openWay;
    private boolean usingYn;
    private String fileName;
    private String urlFileName;
    private LocalDateTime createAt;
    private long totalCnt;
    private long seq;
    private String delIds;

    public static BannerDto from(Banner banner) {
        return BannerDto.builder()
                .id(banner.getId())
                .name(banner.getName())
                .url(banner.getUrl())
                .sortVal(banner.getSortVal())
                .openWay(banner.getOpenWay())
                .usingYn(banner.isUsingYn())
                .fileName(banner.getFileName())
                .urlFileName(banner.getUrlFileName())
                .createAt(banner.getCreateAt())
                .build();
    }
}
