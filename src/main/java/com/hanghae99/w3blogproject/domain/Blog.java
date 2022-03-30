package com.hanghae99.w3blogproject.domain;

import com.hanghae99.w3blogproject.dto.BlogRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter // lombok
@NoArgsConstructor // 기본생성자를 대신 생성해줍니다.
@Entity // 테이블임을 나타냅니다.

// 만들어둔 Timestamped 객체를 Blog 클래스에 가져다 쓸게 !
public class Blog extends Timestamped {

    @Id // ID 값, Primary Key로 사용하겠다는 뜻입니다.
    @GeneratedValue(strategy = GenerationType.AUTO) // 자동 증가 명령입니다.
    private Long id;

    @Column(nullable = false) // 컬럼 값이고 반드시 값이 존재해야 함을 나타냅니다.
    private String title;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String content;

    // 유저 아이디 값은 왜 필요할까요?
    @Column (nullable = false)
    private Long userId;

    // private 정보를 정해진 방법으로만 불러오는 메소드
//    public Long getId() {
//        return this.id;
//    }
//
//    public String getTitle() {
//        return this.title;
//    }
//
//    public String getName() {
//        return this.name;
//    }
//
//    public String getContent() {
//        return this.content;
//    }

    // title, username, userId, content 생성할 때부터 부여하기 위해 생성자 추가
    public Blog(BlogRequestDto requestDto, Long userId, String username) {
        this.userId = userId;
        this.title = requestDto.getTitle();
        this.username = username;
        this.content = requestDto.getContent();
    }

    // blog라는 걸 받았을 때, 그 안에 있는 username, title, content를 직접 업데이트 해주는 메소드
    public void update(BlogRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
//        this.username = requestDto.getUsername();
    }

}