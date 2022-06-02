package kr.or.ddit.basic.session;

import java.sql.SQLException;
import com.ibatis.sqlmap.client.SqlMapClient;
import util.SqlMapClientFactory;

public class iBatis_SessionServiceImpl implements iBatis_ISessionService {

	private iBatis_ISessionDao dao;
	private static iBatis_SessionServiceImpl service;
	private SqlMapClient smc;
	private iBatis_SessionServiceImpl() {
		dao = iBatis_SessionDaoImpl.getInstance();
		smc = SqlMapClientFactory.getSqlMapClient();
	}
	public static iBatis_SessionServiceImpl getInstance() {
		if(service == null) service = new iBatis_SessionServiceImpl();
		return service;
	}
	
	@Override
	public iBatis_MemberVO getOneMember(iBatis_MemberVO loginVo) {
//		SqlMapClient smc = null;
		iBatis_MemberVO memVo = null;
		try {
//			smc = SqlMapClientFactory.getSqlMapClient();
			memVo = dao.getOneMember(smc, loginVo);
		} catch (SQLException e) {
			memVo = null;
			e.printStackTrace();
		}
		return memVo;
	}

}
