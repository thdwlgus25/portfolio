package com.jihyun.portfolio.my.controller;

import com.jihyun.portfolio.category.service.CategoryService;
import com.jihyun.portfolio.member.service.MemberAddService;
import com.jihyun.portfolio.profile.dto.MyProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class WriteController {

    private final CategoryService categoryService;
    private final MemberAddService memberAddService;

    @GetMapping("/write")
    public String write(Model model) {

        // 카테고리
        model.addAttribute("categories", categoryService.getAllCategory());

        // 글쓴이 이름 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String memberEmail = authentication.getName();
        MyProfileDto profile = memberAddService.getMyProfile(memberEmail);
        model.addAttribute("profile", profile);

        return "page/my/Write";
    }

    @GetMapping("/update")
    public String update() {
        return "page/my/Update";
    }

    @GetMapping("/detail")
    public String detail() {
        return "page/my/Detail";
    }

}
