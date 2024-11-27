package com.jihyun.portfolio.social.entity;

import com.jihyun.portfolio.common.entity.BaseEntity;
import com.jihyun.portfolio.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Social extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "social_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_seq")
    private Member member;

    private String github;

    private String blog;

    private String notion;

    private String psEmail;

}
