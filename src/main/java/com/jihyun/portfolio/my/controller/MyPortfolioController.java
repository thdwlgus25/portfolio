package com.jihyun.portfolio.my.controller;

import com.jihyun.portfolio.category.entity.Category;
import com.jihyun.portfolio.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MyPortfolioController {

    private final CategoryService categoryService;

    @GetMapping("/myPortfolio")
    public String myPortfolio(Model model) {

        List<Category> categories = categoryService.getAllCategory();
        model.addAttribute("categories", categories);

        return "page/my/MyPortfolio";
    }
}
