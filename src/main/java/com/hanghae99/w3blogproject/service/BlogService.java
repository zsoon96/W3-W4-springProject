package com.hanghae99.w3blogproject.service;

import com.hanghae99.w3blogproject.domain.Blog;
import com.hanghae99.w3blogproject.dto.BlogRequestDto;
import com.hanghae99.w3blogproject.repository.BlogRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service // 스프링에게 이 클래스 서비스임을 알려주는 어노테이션
public class BlogService {
    
    // final: 서비스에게 꼭 필요한 녀석임을 알려줌
    // 서비스에 선언된 기능들을 통해 Repo에 반영되기 때문에 멤버 변수로 선언
    private final BlogRepository blogRepository;

    // 생성자를 통해, Service 클래스를 만들 때 꼭 Repository를 넣어주도 스프링에게 알려줌
    // 두 줄을 통해 BlogRepository를 언제든 쓸 수 있게 스프링이 생성해서 넘겨줌
     public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Transactional // SQL 쿼리가 일어나야 함을 스프링에게 알려주는 어노테이션
    // DB에 업데이트된 정보를 반영해주는 어노테이션
    // 업데이트 부분 (업데이트 할 대상, 업데이트할 정보를 가져올 녀석)
    public Long update(Long id, BlogRequestDto requestDto){
        Blog blog = blogRepository.findById(id).orElseThrow(
                () -> new NullPointerException("게시물이 존재하지 않습니다.")
        );
        blog.update(requestDto);
        return blog.getId();
    }
    // 1. update라는 메소드를 통해 Long 타입으로 반환해줘 ( 변경할 대상 id와 변경할 정보 dto를 활용해서 !)
    // 2. blog Repo에서 해당 id값에 대한 정보를 찾아서 blog 객체에 담아줘!
    // 3. 단, id가 없을 경우엔 예외처리 부탁해
    // 4. blog의 해당 id에 dto의 정보를 반영해줘
    // 5. blog의 id값으로 반환해줘

}
