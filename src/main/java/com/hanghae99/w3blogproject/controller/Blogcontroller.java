package com.hanghae99.w3blogproject.controller;

import com.hanghae99.w3blogproject.domain.Blog;
import com.hanghae99.w3blogproject.repository.BlogRepository;
import com.hanghae99.w3blogproject.dto.BlogRequestDto;
import com.hanghae99.w3blogproject.security.UserDetailsImpl;
import com.hanghae99.w3blogproject.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequiredArgsConstructor
//서버가 데이터에 대한 응답을 줄 때 사용 = JSON형식으로 데이터를 주는 자동응답기
@RestController
public class Blogcontroller {

    // BlogRepository(SQL)가 꼭 필요하기에 멤버변수로 설정
    private final BlogRepository blogRepository;

    private final BlogService blogService;

    // PostMapping을 통해서, 같은 주소라도 방식이 다름을 구분합니다.
    @PostMapping("/api/blogs")
    public Blog createBlog(@RequestBody BlogRequestDto requestDto) {
        // RequestBody : Body라는 녀석에 들어있는 데이터를 DTO에 잘 넣어줘!
        // requestDto 는, 생성 요청을 의미합니다.
        // 강의 정보를 만들기 위해서는 강의 제목과 튜터 이름이 필요하잖아요?
        // 그 정보를 가져오는 녀석입니다.

        // 저장하는 것은 Dto가 아니라 Blog이니, Dto의 정보를 blog에 담아야 합니다.
        // 잠시 뒤 새로운 생성자를 만듭니다.
        Blog blog = new Blog(requestDto);

        // JPA를 이용하여 DB에 저장하고, 그 결과를 반환합니다.
        return blogRepository.save(blog);
    }


    // /api/blogs라는 주소로 데이터 조회 요청이 오면 아래의 메소드를 실행
    @GetMapping("/api/blogs")
    public List<Blog> getBlog() {
        return blogRepository.findAllByOrderByModifiedAtDesc();
    }

    @GetMapping("/api/blogs/detail/{id}")
    public Blog getDetail (@PathVariable Long id) {
        return blogRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("null"));
}

    @PutMapping("/api/blogs/detail/{id}") // PathVariable: 경로에 있는 변수
    public Long updateBlog(@PathVariable Long id, @RequestBody BlogRequestDto requestDto) {
        return blogService.update(id, requestDto);
    }

    @DeleteMapping("/api/blogs/{id}")
    public Long deleteBlog(@PathVariable Long id) {
        blogRepository.deleteById(id);
        return id;
    }


    @GetMapping("/api/blogs/comments")
    public ModelAndView getOneBlogAndComments(@RequestParam Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return blogService.getOneBlogAndComments(id, userDetails);
    }
}
