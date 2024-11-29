package com.jihyun.portfolio.member.service;

import com.jihyun.portfolio.member.entity.Member;
import com.jihyun.portfolio.member.repository.MemberRepository;
import com.jihyun.portfolio.profile.dto.MyProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberAddService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public Member saveMember(Member member) {
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member) {

        Optional<Member> mem = memberRepository.findByMemberEmail(member.getMemberEmail());
        if(mem.isPresent()) {
            Member m = mem.get();
            throw new IllegalStateException(m + " : 이미 존재하는 회원입니다.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String memberEmail) throws UsernameNotFoundException {
        Member member = memberRepository.findByMemberEmail(memberEmail)
                .orElseThrow(() -> new UsernameNotFoundException("해당 사용자가 존재하지 않습니다."));

        return User.builder()
                .username(member.getMemberEmail())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();
    }

    // 사용자 프로필 정보 가져오기 (myProfile)
    public MyProfileDto getMyProfile(String memberEmail) {
        Member member = memberRepository.findByMemberEmail(memberEmail)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다." + memberEmail));

        return MyProfileDto.builder()
                .memberName(member.getMemberName())
                .memberEmail(member.getMemberEmail())
                .build();
    }
}
