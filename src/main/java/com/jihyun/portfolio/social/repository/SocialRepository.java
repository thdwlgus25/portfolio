package com.jihyun.portfolio.social.repository;

import com.jihyun.portfolio.social.entity.Social;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialRepository extends JpaRepository<Social, Long>, SocialCustomRepository {
}
