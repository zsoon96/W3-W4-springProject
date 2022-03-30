package com.hanghae99.w3blogproject.controller;

import com.hanghae99.w3blogproject.domain.Comment;
import com.hanghae99.w3blogproject.dto.CommentRequestDto;
import com.hanghae99.w3blogproject.security.UserDetailsImpl;
import com.hanghae99.w3blogproject.service.BlogService;
import com.hanghae99.w3blogproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;
    private final BlogService blogService;

    @PostMapping("/api/blogs/comments")
    public Comment creatComment(@RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.creatComment(requestDto, userDetails);
    }

    @DeleteMapping("/api/blogs/comments/{id}")
    public Long deleteComment(@PathVariable Long id){
       return commentService.deleteComment(id);
    }

    @PutMapping("/api/blogs/comments/{id}")
    public Long updateComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto) {
        return commentService.update(id, requestDto);
    }

    @GetMapping("/api/blogs/comments")
    public ModelAndView getOneBlogAndComments(@RequestBody @RequestParam Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return blogService.getOneBlogAndComments(id, userDetails);
    }
}
