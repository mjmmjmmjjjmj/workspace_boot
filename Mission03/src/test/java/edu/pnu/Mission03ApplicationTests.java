package edu.pnu;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import edu.pnu.dao.MemberDao;
import edu.pnu.domain.MemberVO;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class Mission03ApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private MemberDao memberDao;
	
	@Test
	public void test() { 
		List<MemberVO> list = memberDao.getAllMember();
		for (MemberVO m : list)
			System.out.println(m);
	}
}
