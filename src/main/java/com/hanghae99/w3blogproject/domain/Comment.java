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

public class Comment extends Timestamped{
    @Id
    // 데이터베이스별 id 값 번호 부여 (순서)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 댓글 번호 생성
    private Long commentId;

    // 작성자 생성
    @Column (nullable = false)
    private String username;

    // 댓글 내용 생성
    @Column (nullable = false)
    private String comment;

    // 게시글 번호 생성
    @Column (nullable = false)
    private Long blogId;


    // 변경될 정보(댓글 내용, 게시글 번호) 가 담긴 생성자 추가
    public Comment(CommentRequestDto requestDto, String username) {
        this.comment = requestDto.getComment();
        this.blogId = requestDto.getBlogId();
        this.username = username;
    }

    // 업데이트 할 정보(댓글 내용, 게시글 번호)가 담긴 메소드..? 추가
    public long update(CommentRequestDto requestDto){
        this.comment = requestDto.getComment();
        this.blogId = requestDto.getBlogId();
        // Dto에 담겨진 comment와 blogId를 기존에서 업데이트 하고,
        // 댓글 번호로 리턴해줘(누가 바뀌었는지 기준을 잡아서 알려주기 위해?)
        return commentId;
    }
}
