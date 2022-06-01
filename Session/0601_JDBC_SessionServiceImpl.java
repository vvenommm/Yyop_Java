package kr.or.ddit.basic.session;

import java.sql.SQLException;
import com.ibatis.sqlmap.client.SqlMapClient;
import util.SqlMapClientFactory;

public class JDBC_SessionServiceImpl implements JDBC_ISessionService {

	private JDBC_ISessionDao dao;
	private static JDBC_SessionServiceImpl service;
	private JDBC_SessionServiceImpl() {
		dao = JDBC_SessionDaoImpl.getInstance();
	}
	public static JDBC_SessionServiceImpl getInstance() {
		if(service == null) service = new JDBC_SessionServiceImpl();
		return service;
	}
	
	@Override
	public JDBC_MemberVO getOneMember(String mem_id) {
		SqlMapClient smc = null;
		JDBC_MemberVO vo = null;
		
		try {
			smc = SqlMapClientFactory.getSqlMapClient();
			vo = dao.getOneMember(smc, mem_id);
		} catch (SQLException e) {
			vo = null;
			e.printStackTrace();
		}
		return vo;
	}

}
