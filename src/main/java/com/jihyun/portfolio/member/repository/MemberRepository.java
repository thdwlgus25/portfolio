package com.jihyun.portfolio.member.repository;

import com.jihyun.portfolio.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberCustomRepository {
}
