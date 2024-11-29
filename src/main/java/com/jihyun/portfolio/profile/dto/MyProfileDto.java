package com.jihyun.portfolio.profile.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyProfileDto {

    private String memberName;

    private String memberEmail;
}
