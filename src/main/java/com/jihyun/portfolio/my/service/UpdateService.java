package com.jihyun.portfolio.my.service;

import com.jihyun.portfolio.category.entity.Category;
import com.jihyun.portfolio.category.repository.CategoryRepository;
import com.jihyun.portfolio.my.dto.UpdateDto;
import com.jihyun.portfolio.my.entity.Portfolio;
import com.jihyun.portfolio.my.repository.PortfolioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateService {

    private final PortfolioRepository portfolioRepository;
    private final CategoryRepository categoryRepository;

    public UpdateDto getPortfolioForUpdate(Long seq) {
        Portfolio portfolio = portfolioRepository.findById(seq)
                .orElseThrow(() -> new IllegalArgumentException("Portfolio not found" + seq));
        return UpdateDto.builder()
                .seq(portfolio.getId())
                .title(portfolio.getTitle())
                .regTime(portfolio.getRegTime())
                .memberName(portfolio.getMember().getMemberName())
                .content(portfolio.getContent())
                .build();
    }

    @Transactional
    public void updatePortfolio(Long seq, UpdateDto updateDto) {
        Portfolio portfolio = portfolioRepository.findById(seq)
                .orElseThrow(() -> new IllegalArgumentException("Portfolio not found with seq: " + seq));

        Category category = categoryRepository.findByCategoryName(updateDto.getCategoryName())
                .orElseThrow(() -> new IllegalArgumentException("Category not found: " + updateDto.getCategoryName()));

        portfolio.setTitle(updateDto.getTitle());
        portfolio.setContent(updateDto.getContent());
        portfolio.setCategory(category);

        portfolioRepository.save(portfolio);
    }
}
