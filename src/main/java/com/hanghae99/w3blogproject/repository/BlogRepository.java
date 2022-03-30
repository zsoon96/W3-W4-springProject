package com.hanghae99.w3blogproject.repository;


import com.hanghae99.w3blogproject.domain.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


// JPA Repo를 상속받을 Blog Repo를 만들고,
// 사용할 Entity 객체(Blog)와 해당 id의 컬럼 타입을 상속해온다.?
// 수정일자를 기준 내림차순으로 데이터를 불러와서 List 타입으로 담아줘!
public interface BlogRepository extends JpaRepository<Blog, Long> {
    List<Blog> findAllByOrderByModifiedAtDesc();
}