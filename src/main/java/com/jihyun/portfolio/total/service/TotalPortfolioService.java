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
}
