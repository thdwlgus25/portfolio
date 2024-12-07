package com.jihyun.portfolio.total.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TotalPortfolioDto {

    private Long seq;

    private String title;

    private String categoryName;

    private LocalDateTime regTime;

    private String memberName;

    private String thumbnail;
}
