package com.jihyun.portfolio.total.controller;

import com.jihyun.portfolio.my.dto.DetailDto;
import com.jihyun.portfolio.my.service.DetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class DetailController {

    private final DetailService detailService;

    @GetMapping("/totalPortfolio/{seq}")
    public String getPortfolioDetail(@PathVariable Long seq, Model model) {
        DetailDto detailDto = detailService.getPortfolioDetail(seq);
        model.addAttribute("portfolio", detailDto);
        return "page/my/Detail";
    }
}
