package com.jihyun.portfolio.message;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MessageController {

    @GetMapping("/message")
    public String myPortfolio() {
        return "page/message/Message";
    }
}
