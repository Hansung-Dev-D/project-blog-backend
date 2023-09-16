package com.projectblog.hansung.dto;

import com.projectblog.hansung.entity.BoardEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {
  private Long id;
  private String boardWriter;
  private String boardPass;
  private String boardTitle;
  private String boardContents;
  private int boardHits;
  private LocalDateTime boardCreatedTime;
  private LocalDateTime getBoardUpdatedTime;

  public BoardDto(Long id, String boardWriter, String boardTitle, int boardHits, LocalDateTime boardCreatedTime) {
    this.id = id;
    this.boardWriter = boardWriter;
    this.boardTitle = boardTitle;
    this.boardHits = boardHits;
    this.boardCreatedTime = boardCreatedTime;
  }

  //Entity를 DTO로 변환
  public static BoardDto toBoardDto(BoardEntity boardEntity){
    BoardDto boardDto = new BoardDto();
    boardDto.setId(boardEntity.getId());
    boardDto.setBoardWriter(boardEntity.getBoardWriter());
    boardDto.setBoardPass(boardEntity.getBoardPass());
    boardDto.setBoardTitle(boardEntity.getBoardTitle());
    boardDto.setBoardContents(boardEntity.getBoardContents());
    boardDto.setBoardHits(boardEntity.getBoardHits());
    boardDto.setBoardCreatedTime(boardEntity.getCreatedTime());
    boardDto.setGetBoardUpdatedTime(boardEntity.getUpdatedTime());
    return boardDto;
  }
}
