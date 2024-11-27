package com.jihyun.portfolio.my.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WriteController {

    @GetMapping("/write")
    public String write() {
        return "page/my/Write";
    }

    @GetMapping("/update")
    public String update() {
        return "page/my/Update";
    }

    @GetMapping("/detail")
    public String datail() {
        return "page/my/Detail";
    }

}
