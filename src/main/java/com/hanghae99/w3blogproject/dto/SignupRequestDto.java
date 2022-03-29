package com.hanghae99.w3blogproject.dto;

import lombok.*;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Setter
@Getter
@ToString
@NoArgsConstructor
@Data
public class SignupRequestDto {
    private Long id;

    @NotBlank(message = "닉네임을 입력해 주세요.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=\\S+$).{3,10}",
            message = " 닉네임은 영문 대,소문자와 숫자로 구성하여 3자리 이상 입력해주세요.")
    private String username;

    @NotBlank(message = "비밀번호를 입력해 주세요.")
    @Size(min = 4, max = 15, message = "비밀번호는 최소 4자리 이상 입력해주세요.")
    private String password;

    @NotBlank
    private String passwordConfirm;

//    @AssertFalse (message = "비밀번호를 확인 해주세요.")
//    private boolean ischeckPassword () {
//        if (this.password == this.password2;) {
//            return true;
//        }
//        return false;
//    }

//    private String email;
    private boolean admin = false;
    private String adminToken = "";

}