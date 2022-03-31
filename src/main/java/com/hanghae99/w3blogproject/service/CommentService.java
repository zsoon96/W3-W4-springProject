package com.hanghae99.w3blogproject.service;

import com.hanghae99.w3blogproject.domain.Comment;
import com.hanghae99.w3blogproject.dto.CommentRequestDto;
import com.hanghae99.w3blogproject.repository.CommentRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CommentService {

    // 업데이트 로직 구현 시 db가 필요하기 때문에 Repo를 멤버변수로 선언
    private final CommentRepository commentRepository;

    // 두 줄을 통해 CommentRepository를 언제든 쓸 수 있게 스프링이 생성해서 넘겨줌
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Transactional
    public void updateComment(Long commentId, CommentRequestDto requestDto) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        comment.updateComment(requestDto);
        // 왜 반환값이 없지..?
    }
    // 1. updateComment라는 메소드를 통해 반환타입 없이 반환해줘 ( 변경할 대상 commentId와 변경할 정보 dto를 활용해서 !)
    // 2. comment Repo에서 해당 commentId값에 대한 정보를 찾아서 comment 객체에 담아줘!
    // 3. 단, commentId가 없을 경우엔 예외처리 부탁해
    // 4. comment의 해당 id에 dto의 정보를 반영해줘
}