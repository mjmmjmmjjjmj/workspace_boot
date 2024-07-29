package edu.pnu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.dao.MemberDao;
import edu.pnu.domain.MemberVO; 

//@RequiredArgsConstructor
@Service
public class MemberService {

	@Autowired
	private MemberDao memberDao;
//	private LogDao logDao;
	
	public MemberService() {
		System.out.println("MemberService 생성");
	}

//		memberDao = new MemberDao();
//	}	
	
	public List<MemberVO> getAllMember() {
		try {
			return memberDao.getAllMember();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public MemberVO getMemberById(Integer id) {
		try {
			return memberDao.getMemberById(id);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public MemberVO addMember(MemberVO memberVO) {
		try {
			return memberDao.addMember(memberVO);		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int updateMember(MemberVO memberVO) {
		try {
			return memberDao.updateMember(memberVO);				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 6;
	}
	
	public int deleteMember(Integer id) {
		return memberDao.deleteMember(id);		
	}
}
