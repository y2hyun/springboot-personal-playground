package com.y2hyun.playground.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.y2hyun.playground.service.BoardService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
public class BoardController {

	private final BoardService boardService;
	
	@GetMapping("")
	public String list(Model model, 
			@RequestParam("page") Optional<Integer> page, 
			@RequestParam("size") Optional<Integer> size) {
		
		int pageNo = page.orElse(1);
		int pageSize = size.orElse(BoardService.DEFAULT_PAGE_SIZE);
		
		model.addAttribute("boardList", boardService.searchBoardList(pageNo, pageSize));
		
		return "board/list";
	}
	
	@GetMapping("add")
	public String add(Model model) {
		return "board/form";
	}
}
