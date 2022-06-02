package kr.or.ddit.basic.session;

import java.sql.SQLException;

import com.ibatis.sqlmap.client.SqlMapClient;

public interface iBatis_ISessionDao {

	/**
	 * 회원 ID와 Password가 저장된 MemberVO 객체를 인수값으로 받아서 해당 회원 정보를 반환하는 메소드
	 * @param smc SqlMapClient 객체
	 * @param memVo 회원ID와 PassWord가 저장된 MemberVO객체
	 * @return 로그인 성공 시 해당 회원 정보가 저장된 MemberVO 객체 반환, 실패 시 : null
	 * @throws SQLException
	 */
	public iBatis_MemberVO getOneMember(SqlMapClient smc, iBatis_MemberVO loginVo) throws SQLException;
}
