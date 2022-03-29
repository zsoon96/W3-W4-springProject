package com.hanghae99.w3blogproject.repository;


import com.hanghae99.w3blogproject.domain.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface BlogRepository extends JpaRepository<Blog, Long> {
    List<Blog> findAllByOrderByModifiedAtDesc();
}