package com.hanghae99.w3blogproject.controller;

import com.hanghae99.w3blogproject.domain.Blog;
import com.hanghae99.w3blogproject.dto.BlogRequestDto;
import com.hanghae99.w3blogproject.repository.BlogRepository;
import com.hanghae99.w3blogproject.security.UserDetailsImpl;
import com.hanghae99.w3blogproject.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
//서버가 데이터에 대한 응답을 줄 때 사용 = JSON형식으로 데이터를 주는 자동응답기
@RestController
public class BlogController {

    // BlogRepository(SQL)가 꼭 필요하기에 멤버변수로 설정 -> 데이터를 불러오고 저장하고 삭제하고 수정해야하는 기능이 필요하기 때문에 !!
    // BlogService에 구현된 기능이 필요하기 때문에(update) 멤버변수로 설정
    private final BlogRepository blogRepository;
    private final BlogService blogService;


    // 게시글 생성
    // '/api/blogs'라는 주소로 post 요청이 오면 아래의 메소드를 실행
    // PostMapping을 통해서, 같은 주소라도 방식이 다름을 구분
    @PostMapping("/api/blogs")
    public Blog createBlog(@RequestBody BlogRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();
        Blog blog = new Blog(requestDto, userId, userDetails.getUsername());
        return blogRepository.save(blog);
    }
    // 1. createBlog 메소드를 통해 Blog라는 타입으로 반환해줘 (dto에 담긴 정보와 userdetail의 정보들을 담아서!)
    // 2. 필요한 객체들(타이틀, 내용, 유저 id, 작성자)을 모아 blog라는 객체에 담아줘 !
    // 3. JPA를 통해 blogRepo에 Blog타입의 blog를 저장해줘

    // RequestBody : Body라는 녀석에 들어있는 데이터를 DTO에 잘 넣어줘!
    // requestDto 는 생성 요청을 의미
    // >> 게시글 작성 시 필요한 정보를 가져오는 녀석

    // 저장하는 것은 Dto가 아니라 Blog이니, Dto의 정보를 blog에 담아야 한다.


    // 게시글 목록 조회
    @GetMapping("/api/blogs")
    public List<Blog> getBlog() {
        return blogRepository.findAllByOrderByModifiedAtDesc();
        // 1. getBlog 메소드를 통해 Blog정보들을 List 타입으로 반환해줘 (필요한 재료없이 Repo에서 돌려주기만!)
        // 2. blogRepo에서 모든 정보들을 수정일자 기준 내림차순으로 반환해줘
    }

    // 게시글 상세 조회
    @GetMapping("/api/blogs/{id}")
    public ModelAndView getDetail(@PathVariable("id") Long Id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Optional<Blog> blog = blogRepository.findById(Id);
        ModelAndView modelAndView = new ModelAndView("detail.html");
        modelAndView.addObject("id", blog.get().getId());
        modelAndView.addObject("title", blog.get().getTitle());
        modelAndView.addObject("username", blog.get().getUsername());
        modelAndView.addObject("content", blog.get().getContent());
        modelAndView.addObject("modifiedAt", blog.get().getModifiedAt());
        return modelAndView;
        // 1. detailPage라는 메소드를 통해 ModelAndView 타입으로 조회해줘 (요청경로에 있는 url의 id값과 userdetail의 정보들을 담아서!)
        // 2. blogRepo에서 id값에 해당하는 정보를 blog 객체에 담아줘
        // 3. detail.html로 데이터를 보내주는 modelAndView 객체를 만들어줘
        // 4. modelAndView에서 필요한 정보들을 "변수명"의 "값"으로 실어보내고 싶어
        // 5. modelAndView 객체를 반환해줘

        // Optional = NPE(NullPointerException)== 예외를 방지하기 위해 사용
        // >> Optional은 메소드의 결과가 null이 될 수 있으며, null에 의해 오류가 발생할 가능성이 매우 높을 때 반환값으로만 사용되어야 한다.

        // Model -> model.addAttribute를 사용하여 데이터만 저장
        // ModelAndView -> 데이터를 전송시킬 수 있는 리턴 타입 - 데이터와 이동하고자 하는 View + Page를 같이 저장
    }

    // 게시글 수정
    @PutMapping("/api/blogs/{id}") // PathVariable: 경로에 있는 변수
    public Long updateBlog(@PathVariable Long id, @RequestBody BlogRequestDto requestDto) {
        blogService.update(id, requestDto);
        return id;
        // 1. updateBlog 메소드를 통해 Long 타입으로 반환해줘 (요청 경로에 있는 url의 id값과 dto에 담긴 정보들을 활용해서 !)
        // 2. 해당 변경하고자하는 대상의 id값에 변경된 정보(dto)를 담아 blogService의 update메소드를 통해 업데이트 실행해줘!
        // 3. 변경된 id를 반환해줘
    }

    // 게시글 삭제
    @DeleteMapping("/api/blogs/{id}")
    public Long deleteBlog(@PathVariable Long id) {
        blogRepository.deleteById(id);
        return id;
        // 1. deleteBlog 메소드를 통해 Long 타입으로 반환해줘 (요청경로에 있는 url의 id값을 활용해서!)
        // 2. blogRepo에서 해당 id에 대한 정보를 찾아 삭제해줘
        // 3. 그리고 삭제된 id를 반환해줘
    }

}
