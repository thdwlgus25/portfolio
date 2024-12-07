package com.jihyun.portfolio.my.service;

import com.jihyun.portfolio.my.dto.DetailDto;
import com.jihyun.portfolio.my.entity.Portfolio;
import com.jihyun.portfolio.my.repository.PortfolioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DetailService {

    private final PortfolioRepository portfolioRepository;

    public DetailDto getPortfolioDetail(Long seq) {
        Portfolio portfolio = portfolioRepository.findById(seq)
                .orElseThrow(() -> new IllegalArgumentException("Portfolio not found with seq: " + seq));

        return DetailDto.builder()
                .seq(portfolio.getId())
                .title(portfolio.getTitle())
                .regTime(portfolio.getRegTime())
                .memberName(portfolio.getMember().getMemberName())
                .categoryName(portfolio.getCategory().getCategoryName())
                .content(portfolio.getContent())
                .imagePaths(portfolio.getImagePaths())
                .build();
    }
}
