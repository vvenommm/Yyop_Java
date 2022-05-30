package kr.or.ddit.basic.cookie;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class cookieCountServlet
 */
@WebServlet("/cookieCountServlet.do")
public class CookieCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter(); //출력 스트림
		
		
		//count라는 쿠키 있는지 검사
		Cookie[] cookieArr = request.getCookies();
		
		//저장이 안 되어있을 때 기준으로 0으로 초기화
		int count = 0;
		
		if(cookieArr != null) {
			for(Cookie cookie : cookieArr) {
				if("count".equals(cookie.getName())) {
					//count 쿠키 찾기
					//현재 count 값 구하기
					String value = cookie.getValue();
					count = Integer.parseInt(value); //현재의 count 값 구하기
					break;
				}
			}
		}
		//count 증가
		count ++;
		
		//증가된 값을 count라는 쿠키 키의 값으로 저장
		//쿠키 객체 생성
		Cookie countCookie = new Cookie("count", String.valueOf(count));

		//쿠키 속성 설정 저장
		response.addCookie(countCookie);
		
		//출력
		out.println("<html>");
		out.println("<head><meta charset='utf-8'><title>Cookie Count 연습</title></head>");
		out.println("<body>");
		out.println("<h2>Cookie Count 증가</h2><br><hr>");
		out.println("<h4>웰컴~ "+ count + "번 째 입장~</h4><br><hr>");
		
		
		out.println("<a href='" + request.getContextPath() + "/cookieCountServlet.do'>카운트++</a>");
		out.println("<a href='" + request.getContextPath() + "/basic/cookie/cookieTest02.jsp'>쿠키 홈</a>");
		out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
