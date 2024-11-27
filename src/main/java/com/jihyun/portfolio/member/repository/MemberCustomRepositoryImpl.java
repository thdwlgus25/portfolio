package com.jihyun.portfolio.member.repository;

import com.jihyun.portfolio.member.entity.Member;

import java.util.Optional;

public class MemberCustomRepositoryImpl implements MemberCustomRepository{


    @Override
    public Optional<Member> findByMemberEmail(String memberEmail) {
        return Optional.empty();
    }
}
