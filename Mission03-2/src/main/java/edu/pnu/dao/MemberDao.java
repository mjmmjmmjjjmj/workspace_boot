package edu.pnu.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import edu.pnu.domain.MemberVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class MemberDao {

	private final DataSource dataSource;

//	private Connection con;
	
	// 모든 member 객체를 가져오는데 왜 하나만 리턴을 할까
	// 해결책 : 
	public List<MemberVO> getAllMember() {
		Statement st = null;
		ResultSet rs = null;
		List<MemberVO> list = new ArrayList<>();

//		private MemberVO getMemberVO(ResultSet rs) {
//			return MemberVO.builder()
//					.id(rs.getInt("id"))
//					.pass(rs.getString("pass"))
//					.name(rs.getString("name"))
//					.regidate(rs.getDate("regidate"))
//					.build();
//		}

		try {
			st = dataSource.getConnection().createStatement();
			rs = st.executeQuery("select * from member");
			

			while (rs.next()) {
				MemberVO m = MemberVO.builder().id(rs.getInt("id")).name(rs.getString("name"))
						.pass(rs.getString("pass")).regidate(rs.getDate("regidate")).build();
				list.add(m);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null)
					st.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public MemberVO getMemberById(Integer id) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = dataSource.getConnection().prepareStatement("select * from member where id=?");
			st.setInt(1, id);
			rs = st.executeQuery();
	
			while(rs.next()) {
				MemberVO m = MemberVO.builder()
						.id(rs.getInt("id"))
						.name(rs.getString("name"))
						.pass(rs.getString("pass"))
						.regidate(rs.getDate("regidate"))
						.build();
				return m;
			} 
		} catch (Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				if (st != null) 
					st.close();
				if (rs != null) 
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	private int getMaxId() {
//		select max(id) from member
		Statement st = null;
		ResultSet rs = null;
		
		try {
			st = dataSource.getConnection().createStatement();
			rs = st.executeQuery("select max(id) from member");
	
			while(rs.next()) {
				return rs.getInt(1);
			} 
		} catch (Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				if (st != null) 
					st.close();
				if (rs != null) 
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
	
	public MemberVO addMember(MemberVO memberVO) {
		PreparedStatement st = null;
		
		try {
			// 쿼리문임
			String query = "insert into member (name, pass) values (?, ?)";
			//위에 잇는 쿼리문을 쿼리객체로 생성
			st = dataSource.getConnection().prepareStatement(query);
			// name에 String값 넣기
			st.setString(1, memberVO.getName());
			// pass에 String값 넣기
			st.setString(2, memberVO.getPass());
			// r = query문 실행시킬 놈 + 결과 반환
			int a = st.executeUpdate();
			// ---------------------- 여기까지 데이터 인서트끝
			
			//입력된 데이터를 읽어온다
			//입력된 데이터는 마지막 아이디로 입력되기 때문에 가장 큰 아이디를 읽어온다
			int id = getMaxId();
			//그 아이디에 객체를 읽어서 리턴한다.
			return getMemberById(id);
		}	

//			//첫번째 값 만드는 걸 id로 함 
//			st.setInt(1, memberVO.getId());
//			//두번째 그거를 name으로 함
//			st.setString(2, memberVO.getName());
//			//세번째 그거를 pass로 함
//			st.setString(3, memberVO.getPass());
//			//네번째 그거를 지금 시스템 시간으로 함
//			st.setDate(4, new Date(System.currentTimeMillis()));
//			
//			
//			st.executeQuery(); //쿼리실행
//			return memberVO;
//		}
			// build : 객체존재 x 객체를 만들어내는 함수
			// 커서 프로세싱 : 
//			while(rs.next()) {
//				MemberVO m = MemberVO.builder()
//						.id(rs.getInt("id"))
//						.name(rs.getString("name"))
//						.pass(rs.getString("pass"))
//						.regidate(rs.getDate("regidate"))
//						.build();
//				return m;
//			}
//		}
		catch (Exception e){
			e.printStackTrace();
		}
		finally {
			if (st != null) 
			try {
					st.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
//		if (getMemberById(memberVO.getId()) != null)  
//			return null; 
//		memberVO.setRegidate(new Date());
//		getAllMember().add(memberVO);
//		return null;
//	}
//	
	public int updateMember(MemberVO memberVO) {
		PreparedStatement ps = null;	
		try {
			ps = dataSource.getConnection().prepareStatement("update member SET name = ?, pass = ? where id = ?");
			//connection con : 질의객체 만들려고 connection 호출하는 거
			ps.setString(1,  memberVO.getName());
			ps.setString(2, memberVO.getPass());
			ps.setInt(3, memberVO.getId());
			return ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
//		MemberVO m = getMemberById(memberVO.getId());
//		if (m == null)
//			return 0;
//		m.setName(memberVO.getName());
//		m.setPass(memberVO.getPass());
		return 0;
	}
	
	public int deleteMember(Integer id) {
		PreparedStatement ps = null;
		try {
			ps = dataSource.getConnection().prepareStatement("delete from member where id=?");
			ps.setInt(1, id);

			return ps.executeUpdate();
					
		} catch (Exception e) {
			return 0;
		} finally {
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

}