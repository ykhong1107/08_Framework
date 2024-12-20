package com.kh.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.test.service.BoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("board")
public class BoardController {

	private final BoardService service;
	
	@GetMapping("boardMain")
	public String boardMain() {
		
		
		return "/board/boardMain";
	}
	
	
	
	
	@GetMapping("boardWrite")
	public String boardWrite() {
		
		return "/board/boardWrite";
	}
	
	
	@GetMapping("boardComment")
	public String boardComment() {
		
		return "/board/boardComment";
		
		
		
	}
	
}
