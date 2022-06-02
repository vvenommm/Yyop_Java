package kr.or.ddit.basic.session;

import java.sql.SQLException;

import com.ibatis.sqlmap.client.SqlMapClient;

public class iBatis_SessionDaoImpl implements iBatis_ISessionDao {
	
	private static iBatis_SessionDaoImpl dao;
	private iBatis_SessionDaoImpl() { }
	public static iBatis_SessionDaoImpl getInstance() {
		if(dao == null) dao = new iBatis_SessionDaoImpl();
		return dao;
	}

	@Override
	public iBatis_MemberVO getOneMember(SqlMapClient smc, iBatis_MemberVO loginVo) throws SQLException {
		// TODO 매개변수와 일치하는 사람의 DB정보 가져오기
		iBatis_MemberVO memVo = (iBatis_MemberVO)smc.queryForObject("mymember.getOneMember", loginVo);
		
		return memVo;
	}

}
