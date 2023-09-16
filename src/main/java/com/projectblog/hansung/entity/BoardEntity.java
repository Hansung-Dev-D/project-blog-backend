package com.projectblog.hansung.entity;

import com.projectblog.hansung.dto.BoardDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

//DB의 클래스 역할을 하는 클래스입니다.
@Getter
@Setter
@Entity
@Table(name = "board_table")
public class BoardEntity extends BaseEntity{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 30, nullable = false)
  private String boardWriter;

  @Column
  private String boardPass;

  @Column
  private String boardTitle;

  @Column(length = 500)
  private String boardContents;

  @Column
  private int boardHits;

  public static BoardEntity toSaveEntity(BoardDto boardDto){
    BoardEntity boardEntity = new BoardEntity();
    boardEntity.setBoardWriter(boardDto.getBoardWriter());
    boardEntity.setBoardPass(boardDto.getBoardPass());
    boardEntity.setBoardTitle(boardDto.getBoardTitle());
    boardEntity.setBoardContents(boardDto.getBoardContents());
    boardEntity.setBoardHits(0);
    return boardEntity;
  }

  //업데이트
  public static BoardEntity toUpdateEntity(BoardDto boardDto) {
    BoardEntity boardEntity = new BoardEntity();
    boardEntity.setId(boardDto.getId());
    boardEntity.setBoardWriter(boardDto.getBoardWriter());
    boardEntity.setBoardPass(boardDto.getBoardPass());
    boardEntity.setBoardTitle(boardDto.getBoardTitle());
    boardEntity.setBoardContents(boardDto.getBoardContents());
    boardEntity.setBoardHits(boardDto.getBoardHits());
    return boardEntity;
  }
}






















