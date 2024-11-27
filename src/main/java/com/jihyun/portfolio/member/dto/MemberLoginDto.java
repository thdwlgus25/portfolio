package com.jihyun.portfolio.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberLoginDto {

    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식으로 입력하세요.")
    private String memberEmail;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    private String password;
}
