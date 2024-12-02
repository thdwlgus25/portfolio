package com.jihyun.portfolio.my.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyPortfolioDto {

    private String title;

    private String categoryName;

    private LocalDateTime regTime;

    private String memberName;
}
