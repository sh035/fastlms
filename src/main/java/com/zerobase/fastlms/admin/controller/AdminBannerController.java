package com.zerobase.fastlms.admin.controller;

import com.zerobase.fastlms.admin.dto.BannerDto;
import com.zerobase.fastlms.admin.entity.Banner;
import com.zerobase.fastlms.admin.model.BannerParam;
import com.zerobase.fastlms.admin.service.BannerService;
import com.zerobase.fastlms.course.controller.BaseController;
import com.zerobase.fastlms.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class AdminBannerController extends BaseController {

    private final BannerService bannerService;

    @GetMapping("/admin/banner/list.do")
    public String list(Model model, BannerParam param) {

        param.init();
        List<BannerDto> banners = bannerService.list(param);

        long totalCnt = 0;

        if (banners != null && !banners.isEmpty()) {
            totalCnt = banners.get(0).getTotalCnt();
        }

        String queryString = param.getQueryString();
        String pageHtml = getPaperHtml(totalCnt, param.getPageSize(), param.getPageIndex(), queryString);

        model.addAttribute("banners", banners);
        model.addAttribute("totalCnt", totalCnt);
        model.addAttribute("pager", pageHtml);

        return "admin/banner/list";
    }

    @GetMapping("/admin/banner/add.do")
    public String add(Model model) {
        BannerDto detail = new BannerDto();
        model.addAttribute("detail", detail);
        return "admin/banner/add";
    }

    @PostMapping("/admin/banner/add.do")
    public String addSubmit(MultipartFile file, BannerDto dto) {
        String[] filePath = FileUtil.getFilePath(file);

        dto.setFileName(filePath[0]);
        dto.setUrlFileName(filePath[1]);
        dto.setCreateAt(LocalDateTime.now());

        bannerService.add(dto);
        return "redirect:/admin/banner/list.do";
    }

    @GetMapping("/admin/banner/edit.do")
    public String edit(Model model, BannerDto dto) {
        BannerDto banner = bannerService.findById(dto.getId());
        model.addAttribute("edit", banner);

        return "admin/banner/edit";
    }

//    @PostMapping("/admin/banner/edit.do")
//    public String editSubmit(MultipartFile file, BannerDto dto) {
//        BannerDto banner = bannerService.findById(dto.getId());
//        dto
//    }

    @PostMapping("/admin/banner/delete.do")
    public String delete(BannerDto dto) {
        bannerService.delete(dto.getDelIds());
        return "redirect:/admin/banner/list.do";
    }


}
