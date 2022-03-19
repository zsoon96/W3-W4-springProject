package com.hanghae99.w3blogproject.domain;

import com.sun.tools.javac.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    List<Blog> findAllByOrderByModifiedAtDesc();
}