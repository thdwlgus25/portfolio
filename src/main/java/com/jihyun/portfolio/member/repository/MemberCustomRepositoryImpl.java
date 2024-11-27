package com.jihyun.portfolio.member.repository;

import com.jihyun.portfolio.member.entity.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.jihyun.portfolio.member.entity.QMember.member;

@Repository
@RequiredArgsConstructor
public class MemberCustomRepositoryImpl implements MemberCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<Member> findByMemberEmail(String memberEmail) {

        Member result = jpaQueryFactory
                .selectFrom(member)
                .where(member.memberEmail.eq(memberEmail))
                .fetchOne();

        return Optional.ofNullable(result);
    }
}
