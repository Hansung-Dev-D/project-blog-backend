package com.projectblog.hansung.service;

import com.projectblog.hansung.dto.BoardDto;
import com.projectblog.hansung.entity.BoardEntity;
import com.projectblog.hansung.repository.BoardRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// DTO --> ENTITY
// ENTITY --> DTO

@Service
public class BoardService {
  private final BoardRepository boardRepository;

  public BoardService(BoardRepository boardRepository) {
    this.boardRepository = boardRepository;
  }

  public void save(BoardDto boardDto) {
    BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDto);
    boardRepository.save(boardEntity);
  }

  public List<BoardDto> findAll(){
    List<BoardEntity> boardEntityList = boardRepository.findAll();
    List<BoardDto> boardDtoList = new ArrayList<>();
    for(BoardEntity boardEntity: boardEntityList){
      boardDtoList.add(BoardDto.toBoardDto(boardEntity));
    }
    return boardDtoList;
  }

  @Transactional //추가 메서드 사용을 위해
  public void updateHits(Long id) {
    boardRepository.updateHits(id);
  }

  public BoardDto findById(Long id) {
    Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
    if(optionalBoardEntity.isPresent()){
      BoardEntity boardEntity = optionalBoardEntity.get();
      BoardDto boardDto = BoardDto.toBoardDto(boardEntity);
      return boardDto;
    }else{
      return null;
    }
  }

  public BoardDto update(BoardDto boardDto) {
    BoardEntity boardEntity = BoardEntity.toUpdateEntity(boardDto);
    boardRepository.save(boardEntity);
    return findById(boardDto.getId());
  }

  public void delete(Long id) {
    boardRepository.deleteById(id);
  }

  public Page<BoardDto> paging(Pageable pageable) {
    int page = pageable.getPageNumber() - 1; //page 위치에 있는 값은 0부터 시작
    int pageLimits = 3; //몇개의 글을 볼 것인지
    //페이지당 3개의 글을 보여주고 정렬 기준은 id 기준으로 내림차순 정렬
    Page<BoardEntity> boardEntities = boardRepository.findAll(PageRequest.of(page, pageLimits, Sort.by(Sort.Direction.DESC, "id")));

    System.out.println("boardEntities.getContent() = " + boardEntities.getContent());
    System.out.println("boardEntities.getTotalElements() = " + boardEntities.getTotalElements());
    System.out.println("boardEntities.getNumber() = " + boardEntities.getNumber());
    System.out.println("boardEntities.getTotalPages() = " + boardEntities.getTotalElements());
    System.out.println("boardEntities.getSize() = " + boardEntities.getSize());
    System.out.println("boardEntities.hasPrevious() = " + boardEntities.hasPrevious());
    System.out.println("boardEntities.isFirst() = " + boardEntities.isFirst());
    System.out.println("boardEntities.isLast() = " + boardEntities.isLast());

    //map의 메소드를 이용해서 entity를 BoardDto로 변환시켜준다.
    Page<BoardDto> boardDtos = boardEntities.map(board -> new BoardDto(board.getId(), board.getBoardWriter(), board.getBoardTitle(), board.getBoardHits(), board.getCreatedTime()));
    return boardDtos;
  }
}















