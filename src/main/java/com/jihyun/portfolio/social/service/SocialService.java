package com.jihyun.portfolio.social.service;

import com.jihyun.portfolio.member.entity.Member;
import com.jihyun.portfolio.member.repository.MemberRepository;
import com.jihyun.portfolio.profile.dto.MySocialDto;
import com.jihyun.portfolio.social.entity.Social;
import com.jihyun.portfolio.social.repository.SocialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SocialService {

    private final SocialRepository socialRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void updateSocialData(String memberEmail, MySocialDto mySocialDto) {
        Member member = memberRepository.findByMemberEmail(memberEmail)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다: " + memberEmail));

        Social social = socialRepository.findByMember(member)
                .orElse(Social.builder().member(member).build());

        // 기존 값 유지, 새로운 값만 덮어쓰기
        social.setGithub(mySocialDto.getGithub() != null ? mySocialDto.getGithub() : social.getGithub());
        social.setBlog(mySocialDto.getBlog() != null ? mySocialDto.getBlog() : social.getBlog());
        social.setNotion(mySocialDto.getNotion() != null ? mySocialDto.getNotion() : social.getNotion());

        socialRepository.save(social);
    }

    @Transactional(readOnly = true)
    public MySocialDto getSocialData(String memberEmail) {
        Member member = memberRepository.findByMemberEmail(memberEmail)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다: " + memberEmail));

        Social social = socialRepository.findByMember(member).orElse(null);

        return MySocialDto.builder()
                .github(social != null ? social.getGithub() : "제공되지 않음")
                .blog(social != null ? social.getBlog() : "제공되지 않음")
                .notion(social != null ? social.getNotion() : "제공되지 않음")
                .build();
    }
}
