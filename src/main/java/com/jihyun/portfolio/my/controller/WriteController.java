package com.jihyun.portfolio.my.controller;

import com.jihyun.portfolio.category.service.CategoryService;
import com.jihyun.portfolio.member.service.MemberAddService;
import com.jihyun.portfolio.my.dto.UpdateDto;
import com.jihyun.portfolio.my.dto.WriteDto;
import com.jihyun.portfolio.my.service.UpdateService;
import com.jihyun.portfolio.my.service.WriteService;
import com.jihyun.portfolio.profile.dto.MyProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
public class WriteController {

    private final CategoryService categoryService;
    private final MemberAddService memberAddService;
    private final WriteService writeService;
    private final UpdateService updateService;

    @GetMapping("/write")
    public String write(Model model) {

        // 카테고리
        model.addAttribute("categories", categoryService.getAllCategory());

        // 글쓴이 이름 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String memberEmail = authentication.getName();
        MyProfileDto profile = memberAddService.getMyProfile(memberEmail);
        model.addAttribute("profile", profile);

        // 오늘 날짜 추가
        model.addAttribute("today", LocalDate.now().toString());

        return "page/my/Write";
    }

    @PostMapping("/write")
    public String savePortfolio(@ModelAttribute WriteDto writeDto) {
        // HTML 태그 제거
        String plainTextContent = writeDto.getContent().replaceAll("<[^>]*>", ""); // 모든 HTML 태그 제거
        writeDto.setContent(plainTextContent);

        writeService.savePortfolio(writeDto);
        return "redirect:/myPortfolio"; // 작성 후 리다이렉트
    }

    @GetMapping("/update/{seq}")
    public String updatePortfolio(@PathVariable Long seq, Model model) {
        UpdateDto updateDto = updateService.getPortfolioForUpdate(seq);
        model.addAttribute("portfolio", updateDto);

        model.addAttribute("categories", categoryService.getAllCategory());
        return "page/my/Update";
    }

    @PostMapping("/update/{seq}")
    public String updatePortfolio(@PathVariable Long seq, @ModelAttribute UpdateDto updateDto) {
        updateService.updatePortfolio(seq, updateDto);
        return "redirect:/myPortfolio";
    }

    @GetMapping("/detail")
    public String detail() {
        return "page/my/Detail";
    }

}
