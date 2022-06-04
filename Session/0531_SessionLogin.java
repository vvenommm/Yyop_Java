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
@WebServlet("/SessionLogin.do")
public class SessionLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		//로그인 정보 가져오기
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		
		if(id.equals("admin") && pass.equals("1234")) {
			//session에 값 저장
			session.setAttribute("id", id);
			session.setAttribute("pass", pass);
			
			response.sendRedirect(request.getContextPath()+ "/basic/session/sessionLogin.jsp");
		}else {
			response.sendRedirect(request.getContextPath()+ "/basic/session/sessionLogin.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
