package com.jihyun.portfolio.social.repository;

import com.jihyun.portfolio.member.entity.Member;
import com.jihyun.portfolio.social.entity.Social;

import java.util.Optional;

public interface SocialCustomRepository {

    Optional<Social> findByMember(Member member);
}
