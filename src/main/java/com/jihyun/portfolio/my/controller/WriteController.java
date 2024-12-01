package com.jihyun.portfolio.my.controller;

import com.jihyun.portfolio.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class WriteController {

    private final CategoryService categoryService;

    @GetMapping("/write")
    public String write(Model model) {

        // 카테고리
        model.addAttribute("categories", categoryService.getAllCategory());
        return "page/my/Write";
    }

    @GetMapping("/update")
    public String update() {
        return "page/my/Update";
    }

    @GetMapping("/detail")
    public String detail() {
        return "page/my/Detail";
    }

}
