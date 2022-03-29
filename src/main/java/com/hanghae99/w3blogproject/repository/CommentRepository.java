package com.hanghae99.w3blogproject.repository;

import com.hanghae99.w3blogproject.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 게시글 번호의 생성 일자를 기준으로 모든 데이트를 내림차순으로 List 항목에 담아줘
    List<Comment> findAllByBlogIdOrderByCreatedAtDesc(Long blogId);

}
