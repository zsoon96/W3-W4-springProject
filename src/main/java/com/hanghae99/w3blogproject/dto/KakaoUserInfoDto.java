package com.hanghae99.w3blogproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class KakaoUserInfoDto {
    private Long id;
    private String nickname;
    private String email;

    // lombok의 도움으로 생략 가능 - AllArgsConstructor
//    public KakaoUserInfoDto(Long id, String nickname, String email) {
//        this.id = id;
//        this.nickname = nickname;
//        this.email = email;
//    }
}