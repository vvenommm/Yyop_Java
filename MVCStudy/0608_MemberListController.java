package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberList.do")
public class MemberListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//서블릿에서 실행해야 jsp가 출력됨
		MemberService service = MemberServiceImpl.getInstance();
		
		//바구니 만들기
		List<MemberVO> list = new ArrayList<MemberVO>();
		
		//사진에서 2
		list = service.getAllMemberList();
		
		//8
		request.setAttribute("memberList", list);
		
		//사진에서 9
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/member/memberList.jsp");
		
		//10
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
