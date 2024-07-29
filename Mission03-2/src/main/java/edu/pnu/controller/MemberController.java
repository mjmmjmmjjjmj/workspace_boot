package edu.pnu.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberVO;
import edu.pnu.service.MemberService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class MemberController {
	
	private final MemberService memberService;
	
//	public MemberController() throws SQLException {
//		this.memberService = memberService;
//		System.out.println("MemberController 생성");
//		memberService = new MemberService();
//		memberService.setMemberDao(new MemberDao());
//	}
	
	@GetMapping("/members")
	public ResponseEntity<?> getAllMember() {
		return ResponseEntity.ok(memberService.getAllMember());
	}
	
	
//---------------

	@GetMapping("/member")
	public ResponseEntity<?> getMemberById(Integer id) {
		if (id == null) {
			return ResponseEntity.badRequest().body("getMember failed");
		}
		return ResponseEntity.ok(memberService.getMemberById(id));
	}
	@PostMapping("/member")
	public ResponseEntity<?> addMember(MemberVO memberVO) {
		if (memberVO == null) {
			return ResponseEntity.badRequest().body("addMember failed");
		}
		return ResponseEntity.ok(memberService.addMember(memberVO));
	}
	@PutMapping("/member")
	public ResponseEntity<?> updateMember(MemberVO memberVO) {
		if (memberVO ==  null) {
			return ResponseEntity.badRequest().body("updateMember failed");
		}
		return ResponseEntity.ok(memberService.updateMember(memberVO));
	}
	@DeleteMapping("/member")
	public ResponseEntity<?> deleteMember(@RequestParam Integer id) {
		if (id == null) {
			return ResponseEntity.badRequest().body("삭제가 되나?");
		}
		return ResponseEntity.ok(memberService.deleteMember(id));
	}
}
