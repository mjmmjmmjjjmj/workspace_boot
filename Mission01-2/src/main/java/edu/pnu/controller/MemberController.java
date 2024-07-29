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
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class MemberController {	
	private List<MemberVO> list = new ArrayList<>();
	
	public MemberController() {
		for (int i =1; i<=10; i++) {
			list.add(MemberVO.builder()
					.id(i).name("name" + i)
					.pass("pass" + i)
					.regidate(new Date()).build());
		}
	}
	//검색(Read-select)
	@GetMapping("/members")
	public List<MemberVO> getAllmemMember() {
		return list;
	}
	//검색(Read-select)
	@GetMapping("/member")
	public MemberVO getMemberById(Integer id) {
		for(MemberVO m : list) {
			if(m.getId()==id)
	return m;
				
	}
		return null;
	}
	
}
