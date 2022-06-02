package kr.or.ddit.basic.session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SessionLogin
 */
@WebServlet("/iBatis_SessionLogin.do")
public class iBatis_SessionLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public iBatis_SessionLogin() {
//		service = new JDBC_SessionServiceImpl();
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Get방식으로 요청 시 무조건 sessionLogin.jsp로 이동하기
		
		HttpSession session = request.getSession();
		
		session.invalidate();
		request.getRequestDispatcher("/basic/session/iBatis_SessionLogin.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		//로그인 정보 가져오기
		String mem_id = request.getParameter("id");
		String mem_pass = request.getParameter("pass");
		
		iBatis_MemberVO loginVo = new iBatis_MemberVO();
		loginVo.setMem_id(mem_id);
		loginVo.setMem_pass(mem_pass);
		
		//DB에서 정보 가져오기
		iBatis_ISessionService service = iBatis_SessionServiceImpl.getInstance();
		iBatis_MemberVO memVo = service.getOneMember(loginVo);
		
//		if(id.equals(memVo.getMem_id()) && pass.equals(memVo.getMem_pass())) {
		HttpSession session = request.getSession();
		if(memVo != null) {
			//session에 값 저장
			session.setAttribute("memVo", memVo);
		}
		response.sendRedirect(request.getContextPath()+ "/basic/session/iBatis_SessionLogin.jsp");
	}

}
