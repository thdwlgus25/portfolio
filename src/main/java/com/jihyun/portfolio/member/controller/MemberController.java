package com.jihyun.portfolio.member.controller;

import com.jihyun.portfolio.member.dto.MemberAddDto;
import com.jihyun.portfolio.member.dto.MemberLoginDto;
import com.jihyun.portfolio.member.entity.Member;
import com.jihyun.portfolio.member.service.MemberAddService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberAddService memberAddService;
    private final PasswordEncoder passwordEncoder;

    // 로그인
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("memberLoginDto", new MemberLoginDto());
        model.addAttribute("memberAddDto", new MemberAddDto());
        return "page/login/Login";
    }

    @GetMapping("/login/error")
    public String loginError(Model model) {
        model.addAttribute("loginErrorMsg", "아이디나 패스워드를 다시 확인하세요.");
        return "page/login/Login";
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 로그인한 정보가 있으면
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
            // 콘솔에 로그 출력
            System.out.println("=====> 로그아웃된 사용자: " + authentication.getName());
        } else {
            System.out.println("=====> 로그아웃된 사용자: 인증 정보 없음");
            }
        return "redirect:/";
    }

    // 회원가입
    @GetMapping("/add")
    public String memberAdd(Model model) {
        model.addAttribute("memberAddDto", new MemberAddDto());
        return "page/login/Login";
    }

    @PostMapping("/add")
    public String memberAdd(@Valid @ModelAttribute MemberAddDto memberAddDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "page/login/Login";
        }
        try {
            Member member = Member.createMember(memberAddDto, passwordEncoder);
            memberAddService.saveMember(member);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "page/login/Login";
        }
        return "redirect:/member/login";
    }
}
