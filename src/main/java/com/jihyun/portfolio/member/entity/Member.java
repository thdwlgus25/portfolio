package com.jihyun.portfolio.member.entity;

import com.jihyun.portfolio.common.entity.BaseEntity;
import com.jihyun.portfolio.member.constant.Role;
import com.jihyun.portfolio.member.dto.MemberAddDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    @Column(unique = true, nullable = false)
    private String memberEmail;

    @Column(nullable = false)
    private String password;

    @Column(length = 30, nullable = false)
    private String memberName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false) // 반드시 값이 있어야 함
    private Role role = Role.USER;

    public static Member createMember(MemberAddDto memberAddDto, PasswordEncoder passwordEncoder) {
        return Member.builder()
                .memberName(memberAddDto.getMemberName())
                .memberEmail(memberAddDto.getMemberEmail())
                .password(passwordEncoder.encode(memberAddDto.getPassword()))
                .role(Role.USER)
                .build();
    }
}
