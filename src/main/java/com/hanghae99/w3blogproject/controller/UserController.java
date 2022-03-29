package com.hanghae99.w3blogproject.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hanghae99.w3blogproject.domain.User;
import com.hanghae99.w3blogproject.dto.SignupRequestDto;
import com.hanghae99.w3blogproject.repository.UserRepository;
import com.hanghae99.w3blogproject.security.UserDetailsImpl;
import com.hanghae99.w3blogproject.service.KakaoUserService;
import com.hanghae99.w3blogproject.service.UserService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;
import javax.validation.Valid;


@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final KakaoUserService kakaoUserService;

    private final UserRepository userRepository;
    // 회원 로그인 페이지
    @GetMapping("/user/login")
    public String login(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if(userDetails == null){
            model.addAttribute("user","null");
        }else{

            model.addAttribute("user",userDetails.getUser().getUsername());
        }
        return "login";
    }


    @GetMapping("/user/login/error")
    public String loginError(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails ) {
        if(userDetails == null){
            model.addAttribute("user","null");
        }else{

            model.addAttribute("user",userDetails.getUser().getUsername());
        }
        model.addAttribute("loginError", true);
        return "login";
    }


    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if(userDetails == null){
            model.addAttribute("user","null");
        }else{
            model.addAttribute("user",userDetails.getUser().getUsername());
        }
        model.addAttribute("requestDto", new SignupRequestDto());
        return "signup";
    }

    @PostMapping("/user/signup")
    public String registerUser(Model model, @Valid @ModelAttribute("requestDto") SignupRequestDto requestDto, BindingResult bindingResult){

        // 회원 ID 중복 확인
        Optional<User> found1 = userRepository.findByUsername(requestDto.getUsername()); // Optional을 쓰면 null을 받을 수 있다.

        if(bindingResult.hasErrors()){
//            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors());
            model.addAttribute("user","null");
            return "signup";

        } else if(found1.isPresent()){ // found가 null이 아니면 true를 출력한다.
            FieldError fieldError = new FieldError("requestDto", "username", "이미 존재하는 ID입니다.");
            bindingResult.addError(fieldError);
            return "signup";

        } else if(!requestDto.getPassword().equals(requestDto.getPasswordConfirm())){
            FieldError fieldError = new FieldError("requestDto","passwordConfirm","암호가 일치하지 않습니다.");
            bindingResult.addError(fieldError);
            return "signup";

        } else if(requestDto.getPassword().indexOf(requestDto.getUsername()) != -1) { // indexof가 -1이면 안에 포함이 안되어있는거다.
            FieldError fieldError = new FieldError("requestDto", "password", "비밀번호에 닉네임과 같은값을 넣을수 없습니다.");
            bindingResult.addError(fieldError);
            return "signup";
        }

        userService.registerUser(requestDto);
        return "redirect:/user/login";

    }


    // 카카오 로그인 인가 코드
    @GetMapping("/user/kakao/callback")
    public String kakaoLogin(@RequestParam String code) throws JsonProcessingException {
        kakaoUserService.kakaoLogin(code);
        return "redirect:/";
    }
}

