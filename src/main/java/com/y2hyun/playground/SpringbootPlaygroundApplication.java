package com.y2hyun.playground;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.y2hyun.playground.domain.Board;
import com.y2hyun.playground.domain.User;
import com.y2hyun.playground.domain.enums.BoardType;
import com.y2hyun.playground.repository.BoardRepository;
import com.y2hyun.playground.repository.UserRepository;

@SpringBootApplication
public class SpringbootPlaygroundApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootPlaygroundApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner startUpRunner(final UserRepository userRepository, final BoardRepository boardRepository) {
		
		return new CommandLineRunner() {

			@Override
			public void run(String... args) throws Exception {
				User user = User.builder()
						.name("yang")
						.password("password")
						.email("y2hyun@test.test")
						.createDate(LocalDateTime.now()).build();
				userRepository.save(user);
				
				IntStream.rangeClosed(1, 200).forEach(index ->
					boardRepository.save(Board.builder()
							.title("タイトル" + index)
							.subTitle("サブタイトル" + index)
							.boardType(BoardType.free)
							.contents("タイトル" + index + "のコンテンツです。\r\nははは")
							.createDate(LocalDateTime.now())
							.updateDate(LocalDateTime.now())
							.user(user).build())
				);
			}
			
		};
	}

}
