package com.jihyun.portfolio.my.repository;

import com.jihyun.portfolio.my.dto.MyPortfolioDto;
import com.jihyun.portfolio.my.entity.Portfolio;
import com.jihyun.portfolio.total.dto.TotalPortfolioDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import com.querydsl.core.types.Projections;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.jihyun.portfolio.my.entity.QPortfolio.portfolio;
import static com.jihyun.portfolio.member.entity.QMember.member;
import static com.jihyun.portfolio.category.entity.QCategory.category;

@Repository
@RequiredArgsConstructor
public class PortfolioCustomRepositoryImpl implements PortfolioCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<MyPortfolioDto> findMyPortfolios(String memberEmail) {
        return jpaQueryFactory
                .select(Projections.constructor(MyPortfolioDto.class,
                        portfolio.id,
                        portfolio.title,
                        portfolio.category.categoryName,
                        portfolio.regTime,
                        portfolio.member.memberName
                        ))
                .from(portfolio)
                .where(portfolio.member.memberEmail.eq(memberEmail))
                .orderBy(portfolio.regTime.desc())
                .fetch();
    }

    @Override
    public List<TotalPortfolioDto> findTotalPortfolios() {
        return jpaQueryFactory
                .select(Projections.constructor(TotalPortfolioDto.class,
                        portfolio.id,
                        portfolio.title,
                        category.categoryName,
                        portfolio.regTime,
                        member.memberName
                ))
                .from(portfolio)
                .join(portfolio.category, category)
                .join(portfolio.member, member)
                .orderBy(portfolio.regTime.desc())
                .fetch();
    }
}
