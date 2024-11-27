package com.jihyun.portfolio.main;

import com.jihyun.portfolio.member.dto.MemberAddDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String mainPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof org.springframework.security.core.userdetails.User) {
                org.springframework.security.core.userdetails.User user =
                        (org.springframework.security.core.userdetails.User) principal;
                System.out.println("=====> 현재 로그인 된 회원 정보 : " + user.getUsername());
            }
        } else {
            System.out.println("=====> 현재 로그인 된 회원 정보 : 로그인 정보 없음");
        }

        return "page/main/MainPage";
    }
}
