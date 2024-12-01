package com.jihyun.portfolio.my.repository;

import com.jihyun.portfolio.my.entity.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
}
