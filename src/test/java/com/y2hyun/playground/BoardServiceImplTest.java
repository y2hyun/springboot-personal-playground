package com.y2hyun.playground;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.y2hyun.playground.domain.Board;
import com.y2hyun.playground.service.BoardService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(scripts="classpath:/testscript/board-service-test-data.sql")
public class BoardServiceImplTest {

	@Autowired
	private BoardService boardService;
	
	@Test
	public void test() {
		
		Page<Board> result = boardService.searchBoardList(2, 2);
		assertEquals(result.getNumber(), 1); // 現在ページ （0から）
		assertEquals(result.getTotalElements(), 3); // 全体データ数
		assertEquals(result.getTotalPages(), 2); // 全体ページ数
		assertEquals(result.getNumberOfElements(), 1); // 現在ページのデータ数
		assertEquals(result.getSize(), 2); // ページのデータ数
	}
	
}
