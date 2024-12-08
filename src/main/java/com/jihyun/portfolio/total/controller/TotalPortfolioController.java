package com.jihyun.portfolio.total.controller;

import com.jihyun.portfolio.category.entity.Category;
import com.jihyun.portfolio.category.service.CategoryService;
import com.jihyun.portfolio.total.dto.TotalPortfolioDto;
import com.jihyun.portfolio.total.service.TotalPortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TotalPortfolioController {

    private final CategoryService categoryService;
    private final TotalPortfolioService totalPortfolioService;

    @GetMapping("/totalPortfolio")
    public String totalPortfolio(@RequestParam(value = "query", required = false) String query,
                                 @RequestParam(value = "category", required = false) String categoryName
                                 ,Model model) {

        List<Category> categories = categoryService.getAllCategory();
        model.addAttribute("categories", categories);

        List<TotalPortfolioDto> portfolios;

        // 포트폴리오 목록
        if ((query != null && !query.isEmpty()) || (categoryName != null && !categoryName.isEmpty())) {
            portfolios = totalPortfolioService.searchByTitleAndCategory(query, categoryName);
        } else {
            portfolios = totalPortfolioService.getAllPortfolio();
        }

        model.addAttribute("portfolios", portfolios);
        model.addAttribute("currentCategory", categoryName);
        return "page/total/TotalPortfolio";
    }

}
