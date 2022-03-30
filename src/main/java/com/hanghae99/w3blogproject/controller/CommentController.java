package com.hanghae99.w3blogproject.controller;

import com.hanghae99.w3blogproject.domain.Comment;
import com.hanghae99.w3blogproject.dto.CommentRequestDto;
import com.hanghae99.w3blogproject.repository.CommentRepository;
import com.hanghae99.w3blogproject.security.UserDetailsImpl;
import com.hanghae99.w3blogproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@RestController
public class CommentController {
    // db에 저장하고 불러오고 삭제하고 수정하고 해아히기 때문에(CRUD) Repo를 멤버변수 선언해주기
    // db 업데이트 하는 로직을 사용해야하기 때문에 service를 멤버변수로 선언해주기
    private final CommentRepository commentRepository;
    private final CommentService commentService;

    //댓글 조회
    @GetMapping("/api/blogs/{id}/comments")
    public List<Comment> getComments(@PathVariable("id") Long blogId) {
        return commentRepository.findByBlogIdOrderByModifiedAtDesc(blogId);
        //게시글 번호로 댓글을 조회해서 해당하는 댓글들만 리턴 --> blogId 변수 선언의 이유
        // 1. getComment 메소드를 통해 정보들을 comment라는 리스트 타입으로 반환해줘 (요청 경로에 있는 id값과 blogId 값을 활용해서!)
        // 2. Repo에서 수정일자 기준 내림차순으로 blogId값을 반환해줘
    }

    //댓글 생성
    @PostMapping("/api/blogs/{id}/comments")
    public Comment createComment(@RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        Long userId = userDetails.getUser().getId();
        String username = userDetails.getUsername();
        Comment comment = new Comment(commentRequestDto,userId, username);
        return commentRepository.save(comment);
        //생성시 생성자의 아이디를 받아 함께 저장
        //게시글 아이디는 commentRequestDto에 있음 만들당시 잘 몰라서 여러방식으로 저장함 == bolgId는 필요없는듯?
        // 1. 유저의의 신분 받아오고, 작성자 받아오고, 내용 받아오기
        // 2. 받아온 정보를 comment라는 객체에 넣어주기 !!
        // 3. comment 객체를 db에 저장해주기 ~
    }

    //댓글 수정 -- 경로 유의 (게시글-게시글의 아이디값-댓글-댓글의 아이디값)
    @PutMapping("/api/blogs/{blogId}/comments/{commentId}")
    public Long updateComments(@PathVariable Long commentId, @RequestBody CommentRequestDto requestDto){
        commentService.updateComment(commentId, requestDto);
        return commentId;
        // 1. Long 타입의 아이디값을 반환할건데,
        // 2. 요청 경로에 있는 commentId값과 변경된 정보들을 담고있는 dto 값을 활용을 할거야
        // 3. 어떻게 활용할거냐면, 서비스에 만들어준 updateComment 메소드에 활용을 할거야
        // 4. commentId 기준으로 값을 찾아서 해당 정보를 바꾸고 그걸 commentId값으로 반환해줘!
    }

    //댓글 삭제 -- 경로 유의 (게시글-게시글의 아이디값-댓글-댓글의 아이디값)
    @DeleteMapping("/api/blogs/{blogId}/comments/{commentId}")
    public  String deleteComment(@PathVariable Long commentId,@AuthenticationPrincipal UserDetailsImpl userDetails){
        String name = userDetails.getUser().getUsername();
        String commentUserName = commentRepository.findById(commentId).get().getUsername();
        if (Objects.equals(name, commentUserName)){
            commentRepository.deleteById(commentId);
        } else {
            return "삭제실패";
        }
        return "삭제성공";
        //삭제시 삭제자의 이름과 생성자의이름을 비교해 같으면 삭제 성공하도록 만들었는데 앞단에서 생성자,삭제자의 이름이 다르면 아예 버튼이 안보이도록 만들어서
        //필요없어짐
        // 1. String 타입의 값을 반환할건데,
        // 2. 요청경로에 있는 commentId 값과 사용자 정보를 알려주는 userDetail 클래스를 활용할거야
        // 3. 일단 userDetail에서 user의 username = 작성자를 name에 담아 받아오고,
        // 4. Repo에 commentId 값으로 찾은 username=작성자를 commentUserName에 담아 비교해볼거야 !
        // 5. 만약, 유저와 댓글을 쓴 유저의 객체가 같으면,
        // 6. Repo에서 해당 객체의 commentId값을 삭제해서 성공 메세지를 반환해줘
        // 7. 그게 아니라면 실패 메세지를 반환해줘
    }
}
