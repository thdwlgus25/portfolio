package com.jihyun.portfolio.profile.controller;

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
public class ProfileController {

    private final MemberAddService memberAddService;

    @GetMapping("/myProfile")
    public String myProfile(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String memberEmail = authentication.getName();

        // 사용자 정보 가져오기
        MyProfileDto profile = memberAddService.getMyProfile(memberEmail);
        model.addAttribute("profile", profile); // 프로필 객체 전달

        return "page/profile/MyProfile";
    }
}
