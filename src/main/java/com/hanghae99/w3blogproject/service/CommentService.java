package com.hanghae99.w3blogproject.service;

import com.hanghae99.w3blogproject.domain.Comment;
import com.hanghae99.w3blogproject.dto.CommentRequestDto;
import com.hanghae99.w3blogproject.repository.CommentRepository;
import com.hanghae99.w3blogproject.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    // 데이터들을 Read, Delete, Put을 하기 위해 해당 데이터가 담긴 CommentRepository 불러오기
    private final CommentRepository commentRepository;

    // 해당 데이터(변경된 데이터)를 DB에 반영해줘!
    @Transactional
    // update 메소드명을 통해 Long 타입으로 반환할거야
    // 업데이트에 필요한 재료들을 가지고 (변경될 대상(id), 변경할 정보(dto)
    public Long update(Long id, CommentRequestDto requestDto) {
        // 레포에서 id값을 통해 찾고자하는 대상을 찾아 comment에 담아줘
        Comment comment = commentRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );
        // 대상을 찾았으면, 변경할 정보 dto로 반환해줘!!
        return comment.update(requestDto);
    }

    // createComment라는 메소드명을 통해 Comment(댓글 내용, 작성자, 게시글 번호) 생성자로 반환할거야
    // 저장해야할 재료들을 가지고 (변경된 정보를 담고있는 dto(댓글 내용, 게시글 번호), 유저의 정보(작성자))
    public Comment creatComment(CommentRequestDto requestDto, UserDetailsImpl userDetails) {
        // comment 클래스를 저장하기 위해 comment 클래스를 먼저 생성해주고,
        // 거기다가 저장할 데이터를 담아줘 ( 댓글 내용, 게시글 번호, 작성자)
        Comment comment = new Comment(requestDto, userDetails.getUsername());
        // 대상들을 comment에 담았으면, repository에 저장해줘!
        return commentRepository.save(comment);
    }

//    public List<Comment> showComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto) {
//        return commentRepository.findAllByBlogIdOrderByCreatedAteDesc(id);
//    }

    // deleteComment 메소드명을 통해 id 값을 삭제할거야
    // PathVariable : 경로에 있는(={}) 데이터를 변수로 받는 어노테이션
    public Long deleteComment (@PathVariable Long id){
        // 레포지토리에 해당 아이디 값의 데이터를 삭제해줘!
        commentRepository.deleteById(id);
        // 삭제한 아이디 값을 반환해줘!
        return id;
    }
}
