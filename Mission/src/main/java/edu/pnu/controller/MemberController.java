package edu.pnu.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberVO;


@RestController
public class MemberController {	
	
	public List<MemberVO> list = new ArrayList<>();
	
	public MemberController(){
			for (int i = 1; i<=10; i++) {
			list.add(MemberVO.builder()
				.id(i).name("name"+i)
				.pass("pass" + i)
				.regidate(new Date()).build());
		}	
	}
	// 검색(Read - select)
	@GetMapping("/members")
	public ResponseEntity<?> getAllMember() {
//		return ResponseEntity.ok(list);
		return ResponseEntity.badRequest().body(list);
	}
	
//	@GetMapping("/members")
//	public List<MemberVO> getAllMember() {
//		return list;
//	} 
	
	// 검색(Read - select)
	@GetMapping("/member")
	public MemberVO getMemberByID(Integer id) {
		for(MemberVO m : list) {
			if(m.getId() == id)
				return m;
		}
		return null;
	}
	// 입력(Create - insert)
	@PostMapping("/memberJSON")
	public MemberVO addMemberJSON(MemberVO memberVO) {
		if(getMemberByID(memberVO.getId()) != null)
				return null;
		memberVO.setRegidate(new Date());
		list.add(memberVO);
		return memberVO;
	}
	
	// 수정(Update - update)
	@PutMapping("/member")
	public int updateMembers(MemberVO memberVO) {
		MemberVO m = getMemberByID(memberVO.getId());
		if(m == null)
			return 0;
		m.setName(memberVO.getName());
		m.setPass(memberVO.getPass());
		return 1;
	}
	// 삭제(Delete - delete)
	@DeleteMapping("/member")
	public int removeMember(Integer id) {
		try {
			list.remove(getMemberByID(id));
		} catch(Exception e) {
			return 0;
		}
		return 1;
	}
	
//	@PostMapping("/memberJSON")
//	public MemberVO addMemberJSON(@RequestBody MemberVO memberVO) {
//		if (getMemberByID(memberVO.getId()) != null)
//				return null;
//		memberVO.setRegidate(new Date()); 
//		list.add(memberVO);
//		return memberVO;
//	}
//	@PostMapping("/memberJSON")
//	public MemberVO addMemberJSON(@RequestBody MemberVO memberVO) {
//		return addMember(memberVO);
//	}

}
