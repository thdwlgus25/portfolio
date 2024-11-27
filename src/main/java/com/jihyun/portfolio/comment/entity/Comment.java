package com.jihyun.portfolio.comment.entity;

import com.jihyun.portfolio.common.entity.BaseEntity;
import com.jihyun.portfolio.member.entity.Member;
import com.jihyun.portfolio.my.entity.Portfolio;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_seq")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_seq")
    private Portfolio portfolio;

    private String content;
}
