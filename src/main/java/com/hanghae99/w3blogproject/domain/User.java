package com.hanghae99.w3blogproject.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
public class User {

    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id // pk로 설정
    private Long id;

    // nullable: null 허용 여부
    // unique: 중복 허용 여부 (false 일때 중복 허용)
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

//    @Column(nullable = false, unique = true)
//    private String email;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    @Column(unique = true) // 중복 허용 x
    private Long kakaoId;


    // 일반 로그인
    public User(String username, String password, UserRoleEnum role) {
//        this.id = id;
        this.username = username;
        this.password = password;
//        this.email = email;
        this.role = role;
//        this.kakaoId = null;
    }

    // 카카오 로그인
    public User(String username, String password, UserRoleEnum role, Long kakaoId) {
        this.username = username;
        this.password = password;
//        this.email = email;
        this.role = role;
        this.kakaoId = kakaoId;
    }

//    @OneToMany(mappedBy = "user")
//    List<User> board = new ArrayList<>();

}