package edu.pnu.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberVO;
import edu.pnu.service.MemberService;

@RestController
public class MemberController {
	private MemberService memberService;

//	public MemberController() throws SQLException {
//		memberService = 
//				new MemberService(
//						new MemberDao());
//	}
	public MemberController() {
//		memberDao = new MemberDao();
		memberService = new MemberService();
	}
	
	@GetMapping("/members")
	public ResponseEntity<?> getMember() {
		return ResponseEntity.ok(memberService.getAllMember());
	}
	
	
/////---------------

	@GetMapping("/member")
	public ResponseEntity<?> getMemberVO(Integer id) {
		return ResponseEntity.ok(memberService.getMemberById(id));
	}
	@PostMapping("/member")
	public ResponseEntity<?> addMember(MemberVO memberVO) {
		return ResponseEntity.ok(addMember(memberVO));
	}
	@PutMapping("/member")
	public ResponseEntity<?> updateMember(MemberVO memberVO) {
		return ResponseEntity.ok(memberService.updateMember(memberVO));
	}
	@DeleteMapping("/member")
	public ResponseEntity<?> deleteMember(Integer id) {
		return ResponseEntity.ok(memberService.deleteMember(id));
	}
}
