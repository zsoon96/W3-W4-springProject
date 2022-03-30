package com.hanghae99.w3blogproject.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

// lombok
@Getter
@Setter
// final 이나 NonNull인 파라미터만 추가 작업을 필요로하는 필드에 대한 생성자 추가
@RequiredArgsConstructor

public class BlogRequestDto {
    // 변경될 데이터 목록
//    private final String username;
    private final String title;
    private final String content;
}
