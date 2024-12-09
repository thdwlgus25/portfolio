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
        // 이미지 태그 생성
        StringBuilder contentWithImages = new StringBuilder(portfolio.getContent());
        if (portfolio.getImagePaths() != null && !portfolio.getImagePaths().isEmpty()) {
            for (String imagePath : portfolio.getImagePaths()) {
                contentWithImages.append("<img src='").append(imagePath).append("' style='max-width: 100%;' />");
            }
        }

        return UpdateDto.builder()
                .seq(portfolio.getId())
                .title(portfolio.getTitle())
                .regTime(portfolio.getRegTime())
                .memberName(portfolio.getMember().getMemberName())
                .categoryName(portfolio.getCategory().getCategoryName())
                .content(contentWithImages.toString()) // 내용과 이미지 HTML 포함
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

        // 이미지 경로 업데이트
        if (updateDto.getImagePaths() != null && !updateDto.getImagePaths().isEmpty()) {
            portfolio.setImagePaths(updateDto.getImagePaths());
            portfolio.setThumbnail(updateDto.getImagePaths().get(0)); // 첫 번째 이미지를 썸네일로 설정
        }

        portfolioRepository.save(portfolio);
    }
}
