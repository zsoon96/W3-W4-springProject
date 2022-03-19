package com.hanghae99.w3blogproject.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass // Entity가 자동으로 컬럼으로 인식
@EntityListeners(AuditingEntityListener.class) // 생성, 수정시간을 자동으로 반영하도록 설정하는 어노테이션

// abstract : 추상을 뜻하는 개념
// 무조건 '상속'으로만 사용해야한다는 특징만 우선적으로 기억하기!
public abstract class Timestamped {

    // 생성일자와 수정일자에 대한 멤버변수 추가
    @CreatedDate // 생성일자임을 나타낸다.
    private LocalDateTime createdAt;
    // LocalDateTime은 시간을 나타내는 자바의 자료형 중 하나

    @LastModifiedDate // 마지막 수정일자임을 나타낸다.
    private LocalDateTime modifiedAt;
}
