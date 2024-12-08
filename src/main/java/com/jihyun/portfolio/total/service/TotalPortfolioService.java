package com.jihyun.portfolio.total.service;

import com.jihyun.portfolio.my.repository.PortfolioRepository;
import com.jihyun.portfolio.total.dto.TotalPortfolioDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TotalPortfolioService {

    private final PortfolioRepository portfolioRepository;

    public List<TotalPortfolioDto> getAllPortfolio() {
        return portfolioRepository.findTotalPortfolios();
    }

    public List<TotalPortfolioDto> searchPortfolios(String query) {
        return portfolioRepository.searchByTitle(query)
                .stream()
                .map(portfolio -> TotalPortfolioDto.builder()
                        .seq(portfolio.getId())
                        .title(portfolio.getTitle())
                        .categoryName(portfolio.getCategory().getCategoryName())
                        .regTime(portfolio.getRegTime())
                        .memberName(portfolio.getMember().getMemberName())
                        .thumbnail(portfolio.getThumbnail())
                        .build())
                .toList();
    }

    public List<TotalPortfolioDto> searchByTitleAndCategory(String query, String categoryName) {
        return portfolioRepository.searchByTitleAndCategory(query, categoryName)
                .stream()
                .map(portfolio -> TotalPortfolioDto.builder()
                        .seq(portfolio.getId())
                        .title(portfolio.getTitle())
                        .categoryName(portfolio.getCategory().getCategoryName())
                        .regTime(portfolio.getRegTime())
                        .memberName(portfolio.getMember().getMemberName())
                        .thumbnail(portfolio.getThumbnail())
                        .build())
                .toList();
    }


}
