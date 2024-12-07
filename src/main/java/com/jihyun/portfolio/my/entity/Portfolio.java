package com.jihyun.portfolio.my.entity;

import com.jihyun.portfolio.category.entity.Category;
import com.jihyun.portfolio.common.entity.BaseEntity;
import com.jihyun.portfolio.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Portfolio extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "portfolio_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_seq")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_seq")
    private Category category;

    private String title;

    @Lob
    private String content;

    @ElementCollection
    @CollectionTable(name = "portfolio_image_paths", joinColumns = @JoinColumn(name = "portfolio_id"))
    @Column(name = "image_path")
    private List<String> imagePaths;


}
