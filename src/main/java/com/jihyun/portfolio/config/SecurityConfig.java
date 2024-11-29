package com.jihyun.portfolio.config;

import com.jihyun.portfolio.member.service.MemberAddService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final MemberAddService memberAddService;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.formLogin(form -> form
                .loginPage("/member/login")
                .defaultSuccessUrl("/", true)
                .failureUrl("/member/login/error")
                .usernameParameter("memberEmail")
                .passwordParameter("password")
                .permitAll());
        http.logout(logout -> logout
                .logoutUrl("/member/logout")    // 로그아웃 처리 URL
                .logoutSuccessUrl("/")  // 로그아웃 성공 후 리다이렉트 경로
                .invalidateHttpSession(true) // 세션 무효화
                .deleteCookies("JSESSIONID") // 쿠키 삭제
                .permitAll()
        );

        http.authorizeHttpRequests(request -> request
                .requestMatchers("/", "/member/login", "/member/add","/totalPortfolio", "/Access-denied").permitAll()
                .requestMatchers("/css/**", "/images/**", "/js/**").permitAll()
                .anyRequest().authenticated()
        );

        http.exceptionHandling(exception -> exception
                .authenticationEntryPoint(customAuthenticationEntryPoint)
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
