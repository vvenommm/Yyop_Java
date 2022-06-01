package kr.or.ddit.basic.session;

import java.sql.SQLException;

import com.ibatis.sqlmap.client.SqlMapClient;

public class JDBC_SessionDaoImpl implements JDBC_ISessionDao {
	
	private static JDBC_SessionDaoImpl dao;
	private JDBC_SessionDaoImpl() { }
	public static JDBC_SessionDaoImpl getInstance() {
		if(dao == null) dao = new JDBC_SessionDaoImpl();
		return dao;
	}

	@Override
	public JDBC_MemberVO getOneMember(SqlMapClient smc, String mem_id) throws SQLException {
		// TODO 매개변수와 일치하는 사람의 DB정보 가져오기
		JDBC_MemberVO vo = (JDBC_MemberVO)smc.queryForObject("mymember.getOneMember", mem_id);
		
		return vo;
	}

}
