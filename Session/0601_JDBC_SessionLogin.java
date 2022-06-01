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
@WebServlet("/JDBC_SessionLogin.do")
public class JDBC_SessionLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private JDBC_ISessionService service;
	
	public JDBC_SessionLogin() {
//		service = new JDBC_SessionServiceImpl();
		service = JDBC_SessionServiceImpl.getInstance();
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		//로그인 정보 가져오기
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		
		//DB에서 정보 가져오기
		JDBC_MemberVO vo = service.getOneMember(id);
		
		if(id.equals(vo.getMem_id()) && pass.equals(vo.getMem_pass())) {
			//session에 값 저장
			session.setAttribute("id", id);
			session.setAttribute("pass", pass);
			
			response.sendRedirect(request.getContextPath()+ "/basic/session/jdbc_sessionLogin.jsp");
		}else {
			response.sendRedirect(request.getContextPath()+ "/basic/session/jdbc_sessionLogin.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
