package com.projectblog.hansung.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
//회원가입과 댓글 기능 구현 시 재사용 위해 만드는 클래스
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public class BaseEntity {
  @CreationTimestamp  //생성 시간
  @Column(updatable = false)  //수정시 관여 X
  private LocalDateTime createdTime;

  @UpdateTimestamp  //수정 시간
  @Column(insertable = false) //입력시 관여 X
  private LocalDateTime updatedTime;

}
