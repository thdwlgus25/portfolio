package com.jihyun.portfolio.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberAddDto {

    @NotBlank(message = "이메일은 필수 입력 값 입니다.")
    @Email(message = "이메일 형식으로 입력하세요.")
    private String memberEmail;

    @NotBlank(message = "비밀번호는 필수 입력 값 입니다.")
    @Length(min = 3, max = 15, message = "비밀번호는 3자 이상 15자 이하입니다.")
    private String password;

    @NotEmpty(message = "이름은 필수 입력 값 입니다.")
    private String memberName;


}
