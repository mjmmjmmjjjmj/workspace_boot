package edu.pnu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Member;
import edu.pnu.domain.Role;
import edu.pnu.persistence.MemberRepository;

@Service
public class MemberService {
	@Autowired
	private MemberRepository memberRepository;
	
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	public Member createMember(Member member) {
		System.out.println(member);
		//보안->비번 ** 처리(해시 처리)
		member.setPassword(passwordEncoder.encode(member.getPassword()));
		member.setRole(Role.ROLE_USER);
		return memberRepository.save(member);
	}
	
	public Member getMemberById(Long id) {
		return memberRepository.findById(id).orElse(null);
	}

}
