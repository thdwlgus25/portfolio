package com.jihyun.portfolio.member.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberAddDto {

    private String memberEmail;

    private String password;

    private String memberName;


}
