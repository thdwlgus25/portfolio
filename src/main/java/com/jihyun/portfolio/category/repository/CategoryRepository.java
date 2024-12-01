package com.jihyun.portfolio.category.repository;

import com.jihyun.portfolio.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long>, CategoryCustomRepository {
}
