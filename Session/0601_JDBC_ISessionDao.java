package kr.or.ddit.basic.session;

import java.sql.SQLException;

import com.ibatis.sqlmap.client.SqlMapClient;

public interface JDBC_ISessionDao {

	public JDBC_MemberVO getOneMember(SqlMapClient smc, String mem_id) throws SQLException;
}
