package com.hanghae99.w3blogproject.controller;

import com.hanghae99.w3blogproject.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    // index(main) 화면에서 사용자 이름을 바꿔주기 위해 (동적웹으로 변환) 생성
    // @AuthenticationPrincipal: 로그인된 사용자 정보가 넘어오게 해주는 어노테이션?
    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails == null) {
            return "index";
        }
        model.addAttribute("username", userDetails.getUsername());
        return "index";
    }
}
