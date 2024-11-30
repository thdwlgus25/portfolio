package com.jihyun.portfolio.social.repository;

import com.jihyun.portfolio.member.entity.Member;
import com.jihyun.portfolio.social.entity.Social;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.jihyun.portfolio.social.entity.QSocial.social;

@Repository
@RequiredArgsConstructor
public class SocialCustomRepositoryImpl implements SocialCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<Social> findByMember(Member member) {
        Social result = jpaQueryFactory.selectFrom(social)
                .where(social.member.eq(member))
                .fetchOne();
        return Optional.ofNullable(result);
    }
}
