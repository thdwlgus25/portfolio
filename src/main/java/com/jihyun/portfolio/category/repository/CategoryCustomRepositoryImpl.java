package com.jihyun.portfolio.category.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.jihyun.portfolio.category.entity.QCategory.category;

@Repository
@RequiredArgsConstructor
public class CategoryCustomRepositoryImpl implements CategoryCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;
}
