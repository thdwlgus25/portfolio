package com.jihyun.portfolio.member.controller;

import com.jihyun.portfolio.member.service.MemberAddService;
import com.jihyun.portfolio.profile.dto.MyProfileDto;
import com.jihyun.portfolio.profile.dto.MySocialDto;
import com.jihyun.portfolio.social.service.SocialService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
@RequiredArgsConstructor
public class HeaderController {

    private final MemberAddService memberAddService;
    private final SocialService socialService;

    @ModelAttribute("profile")
    public MyProfileDto addMemberNameToHeader() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 로그인된 사용자가 없을 경우 처리
        if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
            return null; // 로그아웃 상태에서는 null 반환
        }

        // 로그인된 사용자의 이메일로 사용자 정보 가져오기
        String memberEmail = authentication.getName();
        return memberAddService.getMyProfile(memberEmail);
    }

    @ModelAttribute("socialData")
    public MySocialDto addSocialLinksToHeader() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
            return null; // 로그아웃 상태
        }

        String memberEmail = authentication.getName();
        return socialService.getSocialData(memberEmail);
    }

}
