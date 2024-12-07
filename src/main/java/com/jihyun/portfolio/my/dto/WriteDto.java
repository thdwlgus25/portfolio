package com.jihyun.portfolio.my.dto;

import lombok.*;

import java.util.List;

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

    private List<String> imagePaths;
}
