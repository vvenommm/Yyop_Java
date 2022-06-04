package kr.or.ddit.basic.session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.REUtil;

/**
 * Servlet implementation class SessionAdd
 */
@WebServlet("/SessionAdd.do")
public class SessionAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//session 정보 저장하기
		
		/*
			1. Session 객체 생성 혹은 현재 세션 가져오기
				형식1) request객체.getSession(); 또는 Request객체.getSession(true);
					==> 현재 세션이 존재하면 현재 세션 반환, 존재하지 않으면 새 세션 생성
				형식2) request객체.getSession(false);
					==> 현재 세션이 존재하면 젼재 세션 반환, 존재하지 않으면 null반환(새 세션 생성X)
		 */
		HttpSession session = request.getSession();
		
		/*
			2. session 객체의 setAttribute() 메소드 이용하여 저장
				형식) session객체.setAttribute("key값", session값);
					==> 'key값'은 문자열, 'session값'은 모든 종류의 데이터 사용 가능
		 */
		session.setAttribute("testSession", "연습용 세션");
		session.setAttribute("userName", "홍길동");
		session.setAttribute("age", 35);
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head><meta charset='utf-8'><title>Session 연습</title></head>");
		out.println("<body>");
		out.println("<h2>Session 데이터 저장 OK</h2><br><hr>");
		out.println("<a href='" + request.getContextPath() + "/basic/session/sessionTest.jsp'>세션 시작문서로 이동</a>");
		out.println("</body></html>");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
