package com.jihyun.portfolio.my.repository;


import com.jihyun.portfolio.my.dto.MyPortfolioDto;
import com.jihyun.portfolio.my.entity.Portfolio;

import java.util.List;

public interface PortfolioCustomRepository {
    List<MyPortfolioDto> findMyPortfolios(String memberEmail);
}
