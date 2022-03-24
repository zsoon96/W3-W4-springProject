package com.hanghae99.w3blogproject;

import com.hanghae99.w3blogproject.domain.Blog;
import com.hanghae99.w3blogproject.domain.BlogRepository;
import com.hanghae99.w3blogproject.domain.BlogRequestDto;
import com.hanghae99.w3blogproject.service.BlogService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.TimeZone;

@EnableJpaAuditing // 수정일자를 완벽하게 반영하는 어노테이션 = 날짜 자동 업데이트
@SpringBootApplication
public class W3BlogProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(W3BlogProjectApplication.class, args);
    }
    // 시간 설정 코드2 = 안 먹음
    @PostConstruct
    public void started(){
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
    }
}

//    Week02Application.java 의 main 함수 아래에 붙여주세요.
//    JPA 실행코드
//    @Bean
//    public CommandLineRunner demo(BlogRepository blogRepository, BlogService blogService) {
//        return (args) -> {
//            // 테이블 생성
//            // Blog Blog1 = new Blog("3주차 항해일지", "양지선", "오늘도 정신없이 불태우고있다 !!");
//
//            // 데이터 저장
//            blogRepository.save(new Blog("3주차 항해일지", "양지선", "오늘도 정신없이 불태우고있다 !!"));
//
//            // 데이터 전체 조회
//            List<Blog> blogList = blogRepository.findAll();
//            // 반복문을 통해 조회한 내용 찍어주기
//            for (int i = 0; i < blogList.size(); i++) {
//                Blog blog = blogList.get(i);
//                System.out.println(blog.getId());
//                System.out.println(blog.getTitle());
//                System.out.println(blog.getName());
//                System.out.println(blog.getContent());
//            }
//            // 데이터 하나 조회
//            // Blog blog = repository.findById(1L).orElseThrow(
//            //        () -> new NullPointerException("해당 아이디는 존재하지 않습니다.")
//            // );
//
//            // 데이터 업데이트
//            // Blog new_blog = new Blog("4주차 항해일지", "김태현", "오류와의 싸움은 끝이 나지 않는다^^");
//            BlogRequestDto requestDto = new BlogRequestDto("4주차 항해일지", "김태현", "오류와의 싸움은 끝이 나지 않는다^^");
//            blogService.update(1L, requestDto);
//            blogList = blogRepository.findAll();
//            for (int i = 0; i < blogList.size(); i++) {
//                Blog blog = blogList.get(i);
//                System.out.println(blog.getId());
//                System.out.println(blog.getTitle());
//                System.out.println(blog.getName());
//                System.out.println(blog.getContent());
//            }
//        };
//    }

