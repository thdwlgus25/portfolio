package com.jihyun.portfolio.profile.controller;

import com.jihyun.portfolio.member.service.MemberAddService;
import com.jihyun.portfolio.profile.dto.MyProfileDto;
import com.jihyun.portfolio.profile.dto.MySocialDto;
import com.jihyun.portfolio.social.service.SocialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class ProfileController {

    private final MemberAddService memberAddService;
    private final SocialService socialService;

    @GetMapping("/myProfile")
    public String myProfile(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String memberEmail = authentication.getName();

        // 사용자 정보 가져오기
        MyProfileDto profile = memberAddService.getMyProfile(memberEmail);
        MySocialDto socialData = socialService.getSocialData(memberEmail);

        model.addAttribute("profile", profile);
        model.addAttribute("socialData", socialData);

        return "page/profile/MyProfile";
    }

    @PostMapping("/myProfile")
    @ResponseBody
    public ResponseEntity<?> updateSocialData(@RequestBody Map<String, String> socialData) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String memberEmail = authentication.getName();

        MySocialDto mySocialDto = new MySocialDto();
        mySocialDto.setGithub(socialData.getOrDefault("github", null));
        mySocialDto.setBlog(socialData.getOrDefault("blog", null));
        mySocialDto.setNotion(socialData.getOrDefault("notion", null));

        socialService.updateSocialData(memberEmail, mySocialDto);

        return ResponseEntity.ok(socialData);
    }

}
