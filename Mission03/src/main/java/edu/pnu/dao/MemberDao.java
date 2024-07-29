package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.pnu.domain.MemberVO;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class MemberDao {

	private MemberDao memberDao;
	
	private Connection con;
	
	
	public MemberDao() {
		
		try {
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/.h2/mission", "sa", "1234");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<MemberVO> getAllMember() {
		Statement st = null;
		ResultSet rs = null;
		List<MemberVO> list = new ArrayList<>();
		
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from member");
			
			while(rs.next()) {
				MemberVO m = MemberVO.builder()
					.id(rs.getInt("id"))
					.name(rs.getString("name"))
					.pass(rs.getString("pass"))
					.regidate(rs.getDate("regidate"))
					.build();
				list.add(m);
			} 
		}
		catch(Exception e) {
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
		return list;
		}
	
	public MemberVO getMemberById(Integer id) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = con.prepareStatement("select * from member where id=?");
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
	
	public MemberVO addMember(MemberVO memberVO) {
		if (getMemberById(memberVO.getId()) != null)  
			return null; 
		memberVO.setRegidate(new Date());
		getAllMember().add(memberVO);
		return null;
	}
	
	public int updateMember(MemberVO memberVO) {
		MemberVO m = getMemberById(memberVO.getId());
		if (m == null)
			return 0;
		m.setName(memberVO.getName());
		m.setPass(memberVO.getPass());
		return 1;

	}
	
	public int deleteMember(Integer id) {
		try {
			getAllMember().remove(getMemberById(id));
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

}