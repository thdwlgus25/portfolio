package com.jihyun.portfolio.my.service;

import com.jihyun.portfolio.my.dto.MyPortfolioDto;
import com.jihyun.portfolio.my.repository.PortfolioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPortfolioService {

    private final PortfolioRepository portfolioRepository;

    public List<MyPortfolioDto> getMyPortfolios(String memberEmail) {
        return portfolioRepository.findMyPortfolios(memberEmail);
    }
}
