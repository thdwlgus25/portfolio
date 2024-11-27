package com.jihyun.portfolio.member.repository;

import com.jihyun.portfolio.member.entity.Member;

import java.util.Optional;

public interface MemberCustomRepository {
    Optional<Member> findByMemberEmail(String memberEmail);
}
