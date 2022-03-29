package com.hanghae99.w3blogproject.service;

import com.hanghae99.w3blogproject.domain.Blog;
import com.hanghae99.w3blogproject.domain.Comment;
import com.hanghae99.w3blogproject.repository.BlogRepository;
import com.hanghae99.w3blogproject.dto.BlogRequestDto;
import com.hanghae99.w3blogproject.repository.CommentRepository;
import com.hanghae99.w3blogproject.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.util.List;

@Component
@RequiredArgsConstructor // final 이나 NonNull인 파라미터만 추가 작업을 필요로하는 필드에 대한 생성자 추가
@Service // 스프링에게 이 클래스스 서비스임을 알려주는 어노테이션
public class BlogService {
    
    // final: 서비스에게 꼭 필요한 녀석임을 알려줌
    private final BlogRepository blogRepository;
    private final CommentRepository commentRepository;

    // 생성자를 통해, Service 클래스를 만들 때 꼭 Repository를 넣어주도록
    // 스프링에게 알려줌
    // 두 줄을 통해 BlogRepository를 언제든 쓸 수 있게 스프링이 생성해서 넘겨줌
//     public BlogService(BlogRepository blogRepository) {
//        this.blogRepository = blogRepository;
//    }

//    @Transactional // SQL 쿼리가 일어나야 함을 스프링에게 알려주는 어노테이션
    // DB에 업데이트된 정보를 반영해주는 어노테이션
    // 업데이트 부분 (업데이트 할 아이디, 업데이트할 정보를 가져올 녀석)
    public Long update(Long id, BlogRequestDto requestDto){
        Blog blog1 = blogRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다.")
        );
        blog1.update(requestDto);
        return blog1.getId();
    }

    // 게시글과 해당 게시글의 댓글 조회
    // getOneBlogAndComments 메소드명으로 ModelAndView 타입(데이터를 전송시킬 수 있는 데이터 타입)으로 반환할거야
    // id 값과 userDetails의 재료를 가지고
    public ModelAndView getOneBlogAndComments(Long id, UserDetailsImpl userDetails) {
        // 레포에서 id값을 통해 찾고자하는 대상을 찾아 blog에 담아줘 + 예외처리
        Blog blog = blogRepository.findById(id).orElseThrow(
                () -> new NullPointerException("게시글이 없습니다?")
        );

        // 레포에서 id값의 게시글 번호의 생성 일자를 기준으로 모든 데이트를 내림차순으로 comment라는 List 항목에 담아줘
        List<Comment> comment = commentRepository.findAllByBlogIdOrderByCreatedAtDesc(id);
        // mv라는 ModelAndView 타입 생성
        ModelAndView mv = new ModelAndView();
        // mv의 post라는 페이지를 보여줄거야
        mv.setViewName("post");
        // mv의 data라는 키와 blog(게시글)라는 벨류를 담아 보낼게
        mv.addObject("data", blog);
        // mv의 commentList라는 키와 comment(댓글)라는 벨류를 담아 보낼게
        mv.addObject("commentList", comment);
        // 만약 유저 정보가 null값이 아니라면 (로그인을 한 유저라면)
        if (userDetails != null) {
            // user라는 키에 username 값을 담아 보낼게
            mv.addObject("user", userDetails.getUsername());
        }else
            // 아니라면 user라는 키에 visitor라는 값을 담아 보낼게
            mv.addObject("user", "visitor");
        return mv;
    }
}
