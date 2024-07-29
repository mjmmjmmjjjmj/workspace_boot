package edu.pnu.service;

import java.util.List;

import edu.pnu.dao.MemberDao;
import edu.pnu.domain.MemberVO; 

public class MemberService {

	private MemberDao memberDao;
	
	public MemberService() {
		memberDao = new MemberDao();
	}
	
	public List<MemberVO> getAllMember() {
		return memberDao.getAllMember();
	}
	
	public MemberVO getMemberById(Integer id) {
		return memberDao.getMemberById(id);
	}
	
	public MemberVO addMember(MemberVO memberVO) {
		return memberDao.addMember(memberVO);		
	}
	
	public int updateMember(MemberVO memberVO) {
		return memberDao.updateMember(memberVO);		
	}
	
	public int deleteMember(Integer id) {
		return memberDao.deleteMember(id);		
	}
}
