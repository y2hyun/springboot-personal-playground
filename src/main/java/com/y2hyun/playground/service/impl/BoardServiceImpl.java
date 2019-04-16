package com.y2hyun.playground.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.y2hyun.playground.domain.Board;
import com.y2hyun.playground.repository.BoardRepository;
import com.y2hyun.playground.service.BoardService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {

	private final BoardRepository boardRepository;
	
	@Override
	public Page<Board> searchBoardList(int pageNo, int pageSize) {
		if(pageNo < 1) {
			pageNo = 1;
		}
		if(pageSize < 1) {
			pageSize = DEFAULT_PAGE_SIZE;
		}
		
		Pageable pageable = PageRequest.of(pageNo -1, pageSize, Sort.by(Sort.Order.desc("createDate"), Sort.Order.desc("boardId")));
		return boardRepository.findAll(pageable);
	}

	@Override
	public Board searchBoardDetail(Long boardId) {
		return this.boardRepository.findById(boardId).orElse(null);
	}

}
