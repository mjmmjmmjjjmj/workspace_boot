package edu.pnu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.pnu.domain.Board;
import edu.pnu.domain.Member;
import edu.pnu.service.BoardService;

@SessionAttributes("member")
@Controller
public class BoardController {

	@ModelAttribute("member")
	public Member setmember() {
		return new Member();
	}
	
	@RequestMapping("/getBoardList")
	public String getBoardList(@ModelAttribute("member")Member member, 
												Model model, Board board) {
		if (member.getId() == null) {
			return "redirect:login";
		}
		List<Board> boardList = boardService.getBoardList(board);
		model.addAttribute("boardList", boardList);
		return "getBoardList";
	}
			
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/getBoardList")
	public String getBoardList(Model model, Board board) {
		List<Board> boardList = boardService.getBoardList(board);
		model.addAttribute("boardList", boardList);
		return "getBoardList";
	}
//	
//	@GetMapping("/getBoardList")
//	public String getBoardList(Model model) {
//		
//		List<Board> boardList = new ArrayList<>();
//		for (long i = 1L; i<=10L; i++) {
//			boardList.add(Board.builder()
//					.seq(i)
//					.title("게시판 프로그램 테스트")
//					.writer("도우너")
//					.content("게시판 프로그램 테스트입니다...")
//					.build());
//		}
//		model.addAttribute("boardList", boardList);
//		return "getBoardList";
//	}
	@GetMapping("/insertBoard")
	public String insertBoardView(@ModelAttribute("member") Member member) {
		if (member.getId() == null) {
			return "redirect:login";
		}
		return "insertBoard";
	}
	
	@PostMapping("/insertBoard")
	public String insertBoard(@ModelAttribute("member") Member member,
								Board board) {
		if(member.getId() == null) {
			return "redirect:login";
		}
		boardService.insertBoard(board);
		return "redirect:getBoardList";
	}
	
	@GetMapping("/getBoard")
	public String getBoard(@ModelAttribute("member") Member member,
							Board board, Model model) {
		if(member.getId() == null) {
			return "redirect:login";
		}
		model.addAttribute("board", boardService.getBoard(board.getSeq()));
		return "getBoard";
	}
//	
//	@GetMapping("/getBoard")
//	public String getBoard(Model model, Long seq) {
//		System.out.println("getBoard?seq=" + seq);
//		
//		model.addAttribute("board", boardService.getBoard(seq));
//		return "getBoard";
//	}
//	
//	@GetMapping("/getBoard1")
//	public ModelAndView getBoard1(Long seq) {
//		Board b = boardService.getBoard(seq);
//		
//		org.springframework.web.servlet.ModelAndView mv = new ModelAndView();
//		mv.addObject("board", b);
//		mv.setViewName("getBoard");
//		return mv;
//		
//	}
	@PostMapping("/updateBoard")
	public String updateBoard(@ModelAttribute("member") Member member, 
								Board board, Model model) {
		if(member.getId() == null) {
			return "redirect:login";
		}
		boardService.updateBoard(board);
		return "redirect:getBoardList";
	}
	
	@GetMapping("/deleteBoard")
	public String deleteBoard(@ModelAttribute("member") Member member,
								Board board) {
		if(member.getId() == null) {
			return "redirect:login";
		}
		boardService.deleteBoard(board);
		return "redirect:getBoardList";
	}
}
