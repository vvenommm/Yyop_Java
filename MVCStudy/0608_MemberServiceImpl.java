package kr.or.ddit.member.service;

import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.vo.MemberVO;

public class MemberServiceImpl implements MemberService {
	private static MemberService instance;
	private MemberServiceImpl() {} //기본 생성자를 밖에서 호출할 수 없게
	public static MemberService getInstance() {
		if(instance == null) instance = new MemberServiceImpl();
		return instance;
	}
	
	private MemberDAO dao = MemberDAOImpl.getInstance();
	
	@Override
	public List<MemberVO> getAllMemberList() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		
		//3
		list = dao.getAllMemberList();
		
		//7
		return list;
	}
	
}
