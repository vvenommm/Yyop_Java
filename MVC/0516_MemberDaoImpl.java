package mvcDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mvcVo.MemberVO;

public class MemberDaoImpl implements IMemberDao{

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
			memVo.setMem_id(rset.getString("mem_pass"));
			memVo.setMem_id(rset.getNString("mem_name"));
			memVo.setMem_id(rset.getNString("mem_tel"));
			memVo.setMem_id(rset.getNString("mem_addr"));
		}
		
		if(stat != null) {stat.close();}
		if(rset != null) {rset.close();}
		
		return all;
	}

	@Override
	public int getMemberCount(Connection conn, String memId) throws SQLException {
		String sql = "SELECT COUNT(*) FROM MYMEMBER WHERE MEM_ID = ?";
		PreparedStatement prest = conn.prepareStatement(sql);
		
		prest.setNString(1, memId);
		
		int cnt = prest.executeUpdate();
		
		if(prest != null) {prest.close();}
		
		return cnt;
	}
	
}
