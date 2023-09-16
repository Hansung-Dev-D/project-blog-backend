package com.projectblog.hansung.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {
//  초기화면(index)로 이동
  @GetMapping("/")
  public String index(){
    return "index";
  }
}
