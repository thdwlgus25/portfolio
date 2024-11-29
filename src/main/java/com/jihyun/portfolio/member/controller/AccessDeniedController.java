package com.jihyun.portfolio.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccessDeniedController {

    @GetMapping("/Access-denied")
    public String accessDenied() {
        return "page/login/Access-denied";
    }
}
