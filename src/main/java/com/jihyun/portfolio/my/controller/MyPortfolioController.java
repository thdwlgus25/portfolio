package com.jihyun.portfolio.my.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyPortfolioController {

    @GetMapping("/myPortfolio")
    public String myPortfolio() {
        return "page/my/MyPortfolio";
    }
}
