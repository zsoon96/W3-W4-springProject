package com.hanghae99.w3blogproject.service;

import com.hanghae99.w3blogproject.domain.Blog;
import com.hanghae99.w3blogproject.domain.BlogRepository;
import com.hanghae99.w3blogproject.domain.BlogRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor // final 이나 NonNull인 파라미터만 추가 작업을 필요로하는 필드에 대한 생성자 추가
@Service // 스프링에게 이 클래스스 서비스임을 알려주는 어노테이션
public class BlogService {
    
    // final: 서비스에게 꼭 필요한 녀석임을 알려줌
    private final BlogRepository blogRepository;

    // 생성자를 통해, Service 클래스를 만들 때 꼭 Repository를 넣어주도록
    // 스프링에게 알려줌
    // 두 줄을 통해 BlogRepository를 언제든 쓸 수 있게 스프링이 생성해서 넘겨줌
//     public BlogService(BlogRepository blogRepository) {
//        this.blogRepository = blogRepository;
//    }

    @Transactional // SQL 쿼리가 일어나야 함을 스프링에게 알려주는 어노테이션
    // DB에 업데이트된 정보를 반영해주는 어노테이션

    // 업데이트 부분 (업데이트 할 아이디, 업데이트할 정보를 가져올 녀석)
    public Long update(Long id, BlogRequestDto requestDto){
        Blog blog1 = blogRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다.")
        );
        blog1.update(requestDto);
        return blog1.getId();
    }
}
