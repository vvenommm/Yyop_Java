package kr.or.ddit.basic.session;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

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
@WebServlet("/SessionRead.do")
public class SessionRead extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();

		//session 정보 읽어오기
		
		/*
			1. Session 객체 생성 혹은 현재 세션 가져오기
				형식1) request객체.getSession(); 또는 Request객체.getSession(true);
					==> 현재 세션이 존재하면 현재 세션 반환, 존재하지 않으면 새 세션 생성
				형식2) request객체.getSession(false);
					==> 현재 세션이 존재하면 젼재 세션 반환, 존재하지 않으면 null반환(새 세션 생성X)
		 */
		HttpSession session = request.getSession();
		
		out.println("<html>");
		out.println("<head><meta charset='utf-8'><title>Session 연습</title></head>");
		out.println("<body>");
		out.println("<h2>저장된 Session 정보 확인하기</h2><br><hr><br>");
		out.println("<h3>Session 데이터 1개 확인하기</h3><br>");
		
		/*
			2. session 객체의 getAttribute() 메소드 이용하여 읽어오기
				형식) session객체.getAttribute("key값");
		 */
		String sessionValue = (String)session.getAttribute("testSession");
		
		if(sessionValue == null) {
			out.println("testSession의 세션값이 없습니다.");
		}else {
			out.println("testSession값 : " + sessionValue);
		}
		
		out.println("<br><hr><br>");
		out.println("<h3>전체 세션 데이터 확인하기</h3><br>");
		out.println("<ol>");
		
		Enumeration<String> sessionNames = session.getAttributeNames();
		
		int cnt = 0;
		while(sessionNames.hasMoreElements()) {
			cnt++;
			String sessStringKey = sessionNames.nextElement(); //세션 key값 구하기
			out.println("<li>" + sessStringKey + session.getAttribute(sessStringKey) + "</li>");
		}
		if(cnt == 0) {
			out.println("세션 데이터가 하나도 없슴다");
		}
		out.println("</ol>");

		out.println("<br><hr><br>");
		
		//세션ID ==> 세션을 구분하기 위한 고유한 값
		out.println("세션ID : " + session.getId());
		
		//생성 시간 ==> 1970년 1월 1일부터 경과한 시간(밀리세컨드 단위)
		out.println("세션 생성 시간 : " + session.getCreationTime() + "<br>");
		out.println("세션 최근 접근 시간 : " + session.getLastAccessedTime() + "<br>");
		
		
		//세션 유효 시간 ==> 단위(초) ==> 세션이 유지되는 시간
		//	유효시간 설정은 session.setMaxInactiveInterval(초단위 시간);
		out.println("세션 유효 시간 : " + session.getMaxInactiveInterval() + "<br>");
		
		out.println("<a href='" + request.getContextPath() + "/basic/session/sessionTest.jsp'>세션 시작문서로 이동</a>");
		out.println("</body></html>");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
