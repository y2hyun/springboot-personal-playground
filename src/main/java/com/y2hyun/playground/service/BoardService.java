package com.y2hyun.playground.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.y2hyun.playground.domain.Board;

public interface BoardService {

	int DEFAULT_PAGE_SIZE = 10;
	
	
	/**
	 * 掲示板一覧を取得する
	 * @param pageNo ページ番号 （1から）
	 * @param pageSize ページサイズ（1ページ当たり行数）
	 * @return 掲示板一覧
	 */
	public Page<Board> searchBoardList(int pageNo, int pageSize);
	
	/**
	 * 掲示板内容取得する
	 * @param boardId
	 * @return ない場合null
	 */
	public Board searchBoardDetail(Long boardId);
}
