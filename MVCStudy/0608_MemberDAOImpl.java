package kr.or.ddit.member.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.util.SqlMapClientFactory;
import kr.or.ddit.vo.MemberVO;

public class MemberDAOImpl implements MemberDAO {
	private static MemberDAO instance;
	private MemberDAOImpl() {}
	public static MemberDAO getInstance() {
		if(instance == null) instance = new MemberDAOImpl();
		return instance;
	}
	
	SqlMapClient smc = SqlMapClientFactory.getSqlMapClient();
	
	@Override
	public List<MemberVO> getAllMemberList() {
		//결과 담을 바구니
		List<MemberVO> list = new ArrayList<MemberVO>();
		try {
			//4번
			list = smc.queryForList("member.getAllMemberList");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
