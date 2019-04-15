package com.y2hyun.playground.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.y2hyun.playground.domain.Board;
import com.y2hyun.playground.domain.User;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

	List<Board> findByUser(User user);
}
