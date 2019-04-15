package com.y2hyun.simplebbs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import com.y2hyun.playground.domain.Board;
import com.y2hyun.playground.domain.User;
import com.y2hyun.playground.domain.enums.BoardType;
import com.y2hyun.playground.repository.BoardRepository;
import com.y2hyun.playground.repository.UserRepository;
import com.y2hyun.playground.service.BoardService;

@RunWith(SpringRunner.class)
//@DataJpaTest
@SpringBootTest
public class JpaMappingTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private BoardService boardService;

	
	private final String testEmail = "test@test.com";
	
	@Before
	public void init() {
		User user = User.builder()
				.name("yang")
				.password("password")
				.email(testEmail)
				.createDate(LocalDateTime.now()).build();
		
		this.userRepository.save(user);
		
		Board board = Board.builder()
				.title("テストタイトル")
				.subTitle("サブタイトル")
				.contents("テストコンテンツ")
				.boardType(BoardType.free)
				.createDate(LocalDateTime.now())
				.user(user).build();
		
		Board board2 = Board.builder()
				.title("テストタイトル2")
				.subTitle("サブタイトル2")
				.contents("テストコンテンツ2")
				.boardType(BoardType.free)
				.createDate(LocalDateTime.now())
				.user(user).build();
		
		Board board3 = Board.builder()
				.title("テストタイトル3")
				.subTitle("サブタイトル3")
				.contents("テストコンテンツ3")
				.boardType(BoardType.free)
				.createDate(LocalDateTime.now())
				.user(user).build();
		
		this.boardRepository.saveAll(Arrays.asList(board, board2, board3));
	}
	
	@Test
	public void 保存や検索テスト() {
		User user = this.userRepository.findByEmail(testEmail);
		assertEquals(user.getName(), "yang");
		
		List<Board> boards = this.boardRepository.findByUser(user);
		assertNotNull(boards);
		assertTrue(boards.size() > 0);
		assertEquals(boards.get(0).getTitle(), "テストタイトル");
		assertEquals(boards.get(0).getBoardType(), BoardType.free);
		
		Board noBoard = this.boardRepository.findById(4L).orElse(null);
		assertNull(noBoard);
		
		
		Page<Board> result = boardService.searchBoardList(2, 2);
		assertEquals(result.getNumber(), 1); // 現在ページ （0から）
		assertEquals(result.getTotalElements(), 3); // 全体データ数
		assertEquals(result.getTotalPages(), 2); // 全体ページ数
		assertEquals(result.getNumberOfElements(), 1); // 現在ページのデータ数
		assertEquals(result.getSize(), 2); // ページのデータ数
	}
	
}
