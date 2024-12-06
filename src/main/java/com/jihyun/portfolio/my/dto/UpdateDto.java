package com.jihyun.portfolio.my.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateDto {

    private Long seq;

    private String title;

    private LocalDateTime regTime;

    private String memberName;

    private String categoryName;

    private String content;
}
