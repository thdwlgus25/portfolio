package com.jihyun.portfolio.total;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TotalPortfolioController {

    @GetMapping("/totalPortfolio")
    public String totalPortfolio() {
        return "page/total/TotalPortfolio";
    }
}
