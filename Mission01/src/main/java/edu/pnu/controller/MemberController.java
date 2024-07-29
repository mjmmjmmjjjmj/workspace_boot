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

	private List<MemberVO> list = new ArrayList<>();
	
	public MemberController() {
		for(int i = 1; i<=10; i++) {
			list.add(MemberVO.builder()
					.id(i).name("name"+i)
					.pass("pass"+i)
					.regidate(new Date()).build());
		}
	}
	//검색(read - select)
	@GetMapping("/members")
	public List<MemberVO> getAllMember() {
		return list;
	}
	
	//검색(read- select)
	@GetMapping("/member")
	public MemberVO getMemberById(Integer id) {
		// for 구문에서 for (A : B) ':'의 기능 
		// = B에서 차례대로 객체를 꺼내서 A에 넣겠다
		for (MemberVO m : list) {
			if (m.getId() == id)
				return m;
		}
		return null;			
	}
	
	//입력(create - insert)
	@PostMapping("/member")
	public MemberVO addMember(MemberVO memberVO) {
		// 클라이언트로부터 HTTP POST 요청을 받음, 요청의 데이터는 MemberVO 객체로 받아옴
		if (getMemberById(memberVO.getId()) != null) 
			return null;
		// 회원 ID로 이미 존재하는 회원을 조회
	    // 이미 존재하면 null을 반환하여 회원 추가를 중단함
		memberVO.setRegidate(new Date());
		 // 새로운 회원의 등록 날짜를 현재 날짜로 설정
		list.add(memberVO);
	    // 회원 목록에 새로 생성된 회원 객체를 추가함

		return memberVO;
	    // 추가된 회원 객체를 반환하여 클라이언트에게 응답함
	}
	//수정 (update-update)
	@PutMapping("/member")
	public int updateMembers(MemberVO memberVO) {
		MemberVO m = getMemberById(memberVO.getId());
		if (m == null)
			return 0;
		m.setName(memberVO.getName());
		m.setPass(memberVO.getPass());
		return 1;
	}
	//삭제(delete - delete)
	@DeleteMapping
	public int removeMember(Integer id) {
		try {
			list.remove(getMemberById(id));
		} catch (Exception e) {
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
