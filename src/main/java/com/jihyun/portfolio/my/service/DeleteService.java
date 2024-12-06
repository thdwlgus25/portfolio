package com.jihyun.portfolio.my.service;

import com.jihyun.portfolio.my.entity.Portfolio;
import com.jihyun.portfolio.my.repository.PortfolioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteService {

    private final PortfolioRepository portfolioRepository;

    public void deletePortfolio(Long seq) {
        Portfolio portfolio = portfolioRepository.findById(seq)
                .orElseThrow(() -> new IllegalArgumentException("Portfolio not found: " + seq));

        portfolioRepository.delete(portfolio);
    }
}
