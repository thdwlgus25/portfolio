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
    public String totalPortfolio(@RequestParam(value = "query", required = false) String query, Model model) {

        List<Category> categories = categoryService.getAllCategory();
        model.addAttribute("categories", categories);

        List<TotalPortfolioDto> portfolios;
        if (query != null && !query.isEmpty()) {
            portfolios = totalPortfolioService.searchPortfolios(query);
        } else {
            portfolios = totalPortfolioService.getAllPortfolio();
        }
        model.addAttribute("portfolios", portfolios);

        return "page/total/TotalPortfolio";
    }

}
