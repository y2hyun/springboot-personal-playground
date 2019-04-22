package com.y2hyun.playground;

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
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.y2hyun.playground.domain.Board;
import com.y2hyun.playground.domain.User;
import com.y2hyun.playground.domain.enums.BoardEnums.BoardType;
import com.y2hyun.playground.repository.BoardRepository;
import com.y2hyun.playground.repository.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class JpaMappingTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BoardRepository boardRepository;
	
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
				.boardType(BoardType.FREE)
				.createDate(LocalDateTime.now())
				.user(user).build();
		
		Board board2 = Board.builder()
				.title("テストタイトル2")
				.subTitle("サブタイトル2")
				.contents("テストコンテンツ2")
				.boardType(BoardType.FREE)
				.createDate(LocalDateTime.now())
				.user(user).build();
		
		Board board3 = Board.builder()
				.title("テストタイトル3")
				.subTitle("サブタイトル3")
				.contents("テストコンテンツ3")
				.boardType(BoardType.FREE)
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
		assertEquals(boards.get(0).getBoardType(), BoardType.FREE);
		
		Board noBoard = this.boardRepository.findById(4L).orElse(null);
		assertNull(noBoard);
	}
	
}
