package edu.pnu.service;

import java.util.List;

import edu.pnu.domain.Board;

public interface BoardService {

	List<Board> getBoardList(Board board);

	void insertBoard(Board board);

//	Board getBoard(Long seq);
	
	Board getBoard(Board board);

	void updateBoard(Board board);

	void deleteBoard(Board board);

}