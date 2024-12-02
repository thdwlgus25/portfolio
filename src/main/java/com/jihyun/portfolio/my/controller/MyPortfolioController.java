package com.jihyun.portfolio.my.controller;

import com.jihyun.portfolio.category.entity.Category;
import com.jihyun.portfolio.category.service.CategoryService;
import com.jihyun.portfolio.my.dto.MyPortfolioDto;
import com.jihyun.portfolio.my.service.MyPortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MyPortfolioController {

    private final CategoryService categoryService;
    private final MyPortfolioService myPortfolioService;

    @GetMapping("/myPortfolio")
    public String myPortfolio(Model model) {

        // 현재 로그인한 사용자 이메일 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String memberEmail = authentication.getName();

        // 포트폴리오 데이터 가져오기
        List<MyPortfolioDto> myPortfolios = myPortfolioService.getMyPortfolios(memberEmail);
        model.addAttribute("portfolios", myPortfolios);

        List<Category> categories = categoryService.getAllCategory();
        model.addAttribute("categories", categories);

        return "page/my/MyPortfolio";
    }
}
