package com.jihyun.portfolio.my.repository;

import com.jihyun.portfolio.my.entity.Portfolio;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.jihyun.portfolio.my.entity.QPortfolio.portfolio;

@Repository
@RequiredArgsConstructor
public class PortfolioCustomRepositoryImpl implements PortfolioCustomRepository {
}
