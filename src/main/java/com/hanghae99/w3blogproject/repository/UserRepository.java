package com.hanghae99.w3blogproject.repository;

import com.hanghae99.w3blogproject.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByKakaoId(Long kakaoId);

    boolean existsByUsername(String username);
}