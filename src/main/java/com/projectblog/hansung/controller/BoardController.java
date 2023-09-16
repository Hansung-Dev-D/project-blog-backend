package com.projectblog.hansung.controller;

import com.projectblog.hansung.dto.BoardDto;
import com.projectblog.hansung.repository.BoardRepository;
import com.projectblog.hansung.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
  private final BoardService boardService;
  @GetMapping("/save")
  public String saveForm(){
    return "save";
  }

  @PostMapping("/save")
  public String save(@ModelAttribute BoardDto boardDto) { //하나의 DTO객체로 입력 받음
    System.out.println("boardDto = " + boardDto);
    boardService.save(boardDto);
    return "index"; //index.html 이동
  }

  @GetMapping("/")
  public String findAll(Model model){
    //DB에서 전체 게시글 데이터를 가져와서 list.html에 보여준다.
    List<BoardDto> boardDtoList = boardService.findAll();
    model.addAttribute("boardList", boardDtoList);
    return "list";  //list.html 이동
  }

//  1.조회수를 1 올린다.
//  2. detail.html에 내보낸다
  @GetMapping("/{id}")
  public String findById(@PathVariable Long id, Model model,
                         @PageableDefault(page = 1)Pageable pageable){
    boardService.updateHits(id);
    BoardDto boardDto = boardService.findById(id);
    model.addAttribute("board", boardDto);
    model.addAttribute("page", pageable.getPageNumber());
    return "detail";
  }

  @GetMapping("/update/{id}")
  public String updateForm(@PathVariable Long id, Model model){
    BoardDto boardDto = boardService.findById(id);
    model.addAttribute("boardUpdate", boardDto);
    return "update";
  }

  @PostMapping("/update")
  public String update(@ModelAttribute BoardDto boardDto, Model model){
    BoardDto board = boardService.update(boardDto);
    model.addAttribute("board", board);
    return "detail";
  }

  @GetMapping("/delete/{id}")
  public String delete(@PathVariable Long id){
    boardService.delete(id);
    return "redirect:/board/";
  }

  @GetMapping("/paging")
  public String paging(@PageableDefault(page = 1) Pageable pageable, Model model){
//    pageable.getPageNumber();  //몇 페이지가 요청 되었는지?
    Page<BoardDto> boardList = boardService.paging(pageable);
    int blockLimit = 3;
    int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1; // 1 4 7 10 ~~
    int endPage = ((startPage + blockLimit - 1) < boardList.getTotalPages()) ? startPage + blockLimit - 1 : boardList.getTotalPages();


    // page 갯수 20개
    // 현재 사용자가 3페이지
    // 보여지는 페이지 갯수 3개
    // 만약에 사용자가 5페이지에 있다면 5 6 7

    model.addAttribute("boardList",boardList);
    model.addAttribute("startPage", startPage);
    model.addAttribute("endPage", endPage);
    return "paging";
  }
}


















