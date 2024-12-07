package com.jihyun.portfolio.my.service;

import com.jihyun.portfolio.category.entity.Category;
import com.jihyun.portfolio.category.repository.CategoryRepository;
import com.jihyun.portfolio.member.entity.Member;
import com.jihyun.portfolio.member.repository.MemberRepository;
import com.jihyun.portfolio.my.dto.WriteDto;
import com.jihyun.portfolio.my.entity.Portfolio;
import com.jihyun.portfolio.my.repository.PortfolioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WriteService {

    private final PortfolioRepository portfolioRepository;
    private final CategoryRepository categoryRepository;
    private final MemberRepository memberRepository;

    public void savePortfolio(WriteDto writeDto) {

        // 현재 로그인된 사용자 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        // 사용자 정보 가져오기
        Member member = memberRepository.findByMemberEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다."));

        // 카테고리 정보 가져오기
        Category category = categoryRepository.findById(writeDto.getCategorySeq())
                .orElseThrow(() -> new IllegalArgumentException("카테고리가 존재하지 않습니다."));

        // Portfolio 엔티티 생성
        Portfolio portfolio = Portfolio.builder()
                .title(writeDto.getTitle())
                .content(writeDto.getContent())
                .imagePaths(writeDto.getImagePaths())
                .member(member)
                .category(category)
                .build();

        portfolioRepository.save(portfolio);
    }
}
