package com.hanghae99.w3blogproject.domain;

import com.hanghae99.w3blogproject.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity // 테이블 생성
@Getter
@Setter
@NoArgsConstructor

public class Comment extends Timestamped {
    @Id
    // 데이터베이스별 id 값 번호 부여 (순서)
    @GeneratedValue(strategy = GenerationType.AUTO)
    // 댓글 번호 생성
    private Long id;

    // 작성자 생성
    @Column(nullable = false)
    private String username;

    // 댓글 내용 생성
    @Column(nullable = false)
    private String comment;

    // 게시글 번호 생성
    @Column(nullable = false)
    private Long blogId;

    // 유저 아이디 값은 왜 필요할까요?
    @Column(nullable = false)
    private Long userId;


    // 변경될 정보(댓글 내용, 게시글 번호, 유저 아이디, 작성자) 가 담긴 생성자 추가
    public Comment(CommentRequestDto requestDto, Long userId, String username) {
        this.userId = userId;
        this.comment = requestDto.getComment();
        this.blogId = requestDto.getBlogId();
        this.username = username;
    }

    // 업데이트 할 정보(댓글 내용)가 담긴 메소드..? 추가
    // -> 왜 blogId는 해당이 안될까요? = dto에 담은 이유
    // --> 실질적으로 변경되는 정보는 내용 뿐이니까?!
    public void updateComment(CommentRequestDto requestDto) {
        this.comment = requestDto.getComment();
    }

}
