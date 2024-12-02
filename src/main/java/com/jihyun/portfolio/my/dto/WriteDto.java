package com.jihyun.portfolio.my.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WriteDto {

    private String title;

    private String content;

    private Long categorySeq;
}
