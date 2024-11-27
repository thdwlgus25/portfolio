package com.jihyun.portfolio.member.entity;

import com.jihyun.portfolio.common.entity.BaseEntity;
import com.jihyun.portfolio.member.constant.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_seq")
    private Long id;

    private String memberEmail;

    private String password;

    private String memberName;

    private Role role;
}
