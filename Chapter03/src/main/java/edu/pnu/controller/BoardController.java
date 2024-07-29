package edu.pnu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.BoardVO;
import edu.pnu.service.BoardService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class BoardController {
//	@GetMapping("/hello")
//	public String getMethodName(String name) {
//		return "Hello : " + name;
//	}
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/hello")
	public String hello(String name) {
		log.debug("name: "+ name);
		System.out.println();
		
		return "Hello:"+ name;
//		return boardService.hello(name);
	}
	@GetMapping("/getBoard")
	public BoardVO getBoard() { 
		BoardVO b = new BoardVO();
		b.setWriter("테스트");
		return b;
//		return boardService.getBoard();
	}
	@GetMapping("/getBoardList")
	public List<BoardVO> getBoardList() {
		return boardService.getBoardList();
	}
}
