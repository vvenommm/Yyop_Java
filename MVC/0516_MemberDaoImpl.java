package mvcDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import mvcVo.MemberVO;

public class MemberDaoImpl implements IMemberDao{
	
	//1번
	private static MemberDaoImpl dao;
	
	//2번
	private MemberDaoImpl() { }
	
	//3번
	public static MemberDaoImpl getInstance() {
		if(dao == null) dao = new MemberDaoImpl();
		dao = MemberDaoImpl.getInstance();
		
		return dao;
	}

	@Override
	public int insertMember(Connection conn, MemberVO memVo) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO MYMEMBER VALUES(?, ?, ?, ?, ?)";
		PreparedStatement prest = conn.prepareStatement(sql);
		prest.setNString(1,  memVo.getMem_id());
		prest.setNString(2,  memVo.getMem_pass());
		prest.setNString(3,  memVo.getMem_name());
		prest.setNString(4,  memVo.getMem_tel());
		prest.setNString(5,  memVo.getMem_addr());
		
		int cnt = prest.executeUpdate();
		
		//자원 반납
		if(prest != null) {prest.close();}
		
		return cnt;
	}

	@Override
	public int deleteMember(Connection conn, String memId) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM MYMEMBER WHERE MEM_ID = ?";
		PreparedStatement prest = conn.prepareStatement(sql);
		prest.setNString(1, memId);
		
		int cnt = prest.executeUpdate();
		
		if(prest != null) {prest.close();}
		
		return cnt;
	}

	@Override
	public int updateMember(Connection conn, MemberVO memVo) throws SQLException {
		String sql = "UPDATE MYMEMBER SET MEM_PASS = ?, MEM_NAME = ?, MEM_TEL = ?, MEM_ADDR = ? WHERE MEM_ID = ?";
		PreparedStatement prest = conn.prepareStatement(sql);
		prest.setString(1, memVo.getMem_pass());
		prest.setString(2, memVo.getMem_name());
		prest.setString(3, memVo.getMem_tel());
		prest.setString(4, memVo.getMem_addr());
		prest.setString(5, memVo.getMem_id());
		
		int cnt = prest.executeUpdate();
		
		if(prest != null) try { prest.close(); } catch (SQLException e) { }
		
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMember(Connection conn) throws SQLException {
		String sql = "SELECT * FROM MYMEMBER";
		List<MemberVO> all = null;
		
		Statement stat = conn.createStatement();
		ResultSet rset = stat.executeQuery(sql);
		
		all = new ArrayList<MemberVO>();
			
		while(rset.next()) {
			MemberVO memVo = new MemberVO();
			memVo.setMem_id(rset.getNString("mem_id"));
			memVo.setMem_pass(rset.getString("mem_pass"));
			memVo.setMem_name(rset.getNString("mem_name"));
			memVo.setMem_tel(rset.getNString("mem_tel"));
			memVo.setMem_addr(rset.getNString("mem_addr"));
			all.add(memVo);
		}
		
		if(stat != null) {stat.close();}
		if(rset != null) {rset.close();}
		
		return all;
	}

	@Override
	public int getMemberCount(Connection conn, String memId) throws SQLException {
		String sql = "SELECT COUNT(*) CNT FROM MYMEMBER WHERE MEM_ID = ?";
		PreparedStatement prest = conn.prepareStatement(sql);
		
		prest.setNString(1, memId);
		
		ResultSet rset = prest.executeQuery();
		
		int cnt = 0;
		
		if(rset.next()) {
			cnt = rset.getInt("CNT");
		}
		
		if(prest != null) {prest.close();}
		if(rset != null) {rset.close();}
		
		return cnt;
	}

	@Override
	public int updateMember2(Connection conn, Map<String, String> paramMap) throws SQLException {
		//key값 정보 -> 회원ID(memId), 수정할컬럼명(field), 수정할데이터(data)
		String sql = "UPDATE MYMEMBER SET " + paramMap.get("field") + " = ? " + " WHERE MEM_ID = ?";
		
		PreparedStatement prest = conn.prepareStatement(sql);
		prest.setString(1, paramMap.get("data"));
		prest.setString(2, paramMap.get("memId"));
		
		int cnt = prest.executeUpdate();
		
		if(prest != null) prest.close();
		
		return cnt;
	}
	
}
