package kr.or.ddit.basic.fileupload.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.basic.fileupload.service.FileinfoServiceImpl;
import kr.or.ddit.basic.fileupload.service.IFileinfoService;
import kr.or.ddit.basic.fileupload.vo.MemberVO;

@WebServlet("/memberJoin.do")
public class MemberJoin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//멤버 리스트 가져오기
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		//서비스 객체 생성
		IFileinfoService service = FileinfoServiceImpl.getInstance();
		List<MemberVO> memberList = service.getAllMembers();
		
		//보내기
		request.setAttribute("memberList", memberList);
		response.sendRedirect(request.getContextPath()+ "/basic/fileupload/memberList.jsp");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//회원 가입
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		//정보 가져오기
		String mem_id = request.getParameter("id");
		String mem_pass = request.getParameter("pass");
		String mem_name = request.getParameter("name");
		String mem_addr = request.getParameter("addr");
		String mem_tel = request.getParameter("tel");
		
		MemberVO vo = new MemberVO();
		vo.setMem_id(mem_id);
		vo.setMem_pass(mem_pass);
		vo.setMem_name(mem_name);
		vo.setMem_addr(mem_addr);
		vo.setMem_tel(mem_tel);
		
		//service 객체 생성 후 결과값 받기
		IFileinfoService service = FileinfoServiceImpl.getInstance();
//		List<MemberVO> memberList = service.insertMember(vo);
		service.insertMember(vo);
		
		//정보 보내기
//		request.setAttribute("memberList", memberList);
		response.sendRedirect(request.getContextPath()+ "/basic/fileupload/memberList.jsp");
		
	}

}
