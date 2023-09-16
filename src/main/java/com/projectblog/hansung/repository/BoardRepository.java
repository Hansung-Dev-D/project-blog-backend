package com.projectblog.hansung.repository;

import com.projectblog.hansung.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
  // id를 기준으로 게시물을 클릭시마다 조회수가 1씩 올라간다.
  @Modifying  //업데이트나 삭제를 기능 추가
  @Query(value = "update BoardEntity b set b.boardHits = b.boardHits + 1 where b.id = :id")
  void updateHits(@Param("id") Long id);
}
