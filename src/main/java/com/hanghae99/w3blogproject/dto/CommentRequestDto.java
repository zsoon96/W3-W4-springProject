package com.hanghae99.w3blogproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommentRequestDto {
    //정보를 담고 다닐 목록 (댓글 내용, 게시글 번호)
    private String comment;
    // 뭔가 dto에서 이 정보는 없어도 될듯 !
    //-> 자동적으로 하나씩 부여되는 값이고 고유하기 때문에?
    private Long blogId;
}
