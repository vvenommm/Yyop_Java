package mvcService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import mvcDao.IMemberDao;
import mvcDao.MemberDaoImpl;
import mvcVo.MemberVO;
import util.DBUtil3;

public class MemberServiceImpl implements IMemberService {
	private IMemberDao dao; //DAO 객체가 저장될 변수 선언
	
	public MemberServiceImpl() {
		dao = new MemberDaoImpl();
	}    

	@Override
	public int insertMember(MemberVO memVo) {
		Connection conn = null;
		int cnt = 0; //반환값 저장
		try {
			conn = DBUtil3.getConnection(); //connection 객체 생성
			
			//DAO 메소드 호출
			cnt = dao.insertMember(conn, memVo);
		} catch (SQLException e) {
			// TODO: handle exception
			cnt = 0;
			e.printStackTrace();
		}finally {
			if (conn != null) {
				try {
					conn.close();

				} catch (SQLException e) { }
			}
		}
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		Connection conn = null;
		int cnt = 0;
		
		try {
			conn = DBUtil3.getConnection();
			
			cnt = dao.deleteMember(conn, memId);
		} catch (SQLException e) {
			// TODO: handle exception
			cnt = 0;
			e.printStackTrace();
		}finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO: handle exception
				}
			}
		}
		return cnt;
	}

	@Override
	public int updateMember(MemberVO memVo) {
		Connection conn = null;
		int cnt = 0;
		
		try {
			conn = DBUtil3.getConnection();
			
			cnt = dao.updateMember(conn, memVo);
		} catch (SQLException e) {
			// TODO: handle exception
			cnt = 0;
			e.printStackTrace();
		}finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO: handle exception
				}
			}
		}
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMember() {
		Connection conn = null;
		List<MemberVO> all = null;
		
		try {
			conn = DBUtil3.getConnection();
			
			all = dao.getAllMember(conn);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO: handle exception
				}
			}
		}
		
		return all;
	}

	@Override
	public int getMemberCount(String memId) {
		Connection conn = null;
		int cnt = 0;
		
		try {
			conn = DBUtil3.getConnection();
			
			cnt = dao.getMemberCount(conn, memId);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO: handle exception
				}
			}
		}
		return cnt;
	}

}
