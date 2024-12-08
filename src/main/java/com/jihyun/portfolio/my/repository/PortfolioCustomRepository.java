package com.jihyun.portfolio.my.repository;


import com.jihyun.portfolio.my.dto.MyPortfolioDto;
import com.jihyun.portfolio.my.entity.Portfolio;
import com.jihyun.portfolio.total.dto.TotalPortfolioDto;

import java.util.List;

public interface PortfolioCustomRepository {
    List<MyPortfolioDto> findMyPortfolios(String memberEmail);

    List<TotalPortfolioDto> findTotalPortfolios();

    List<Portfolio> searchByTitle(String query);
}
