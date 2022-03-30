package com.hanghae99.w3blogproject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CommentRequestDto {
    //정보를 담고 다닐 목록 (댓글 내용, 게시글 번호)
    private String comment;
    private Long blogId;
}
