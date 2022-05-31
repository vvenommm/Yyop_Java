package kr.or.ddit.basic.cookie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CookieDelete
 */
@WebServlet("/cookieDelete.do")
public class CookieDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//쿠키 정보 삭제
		
		
		/*
			쿠키 정보 삭제는 남은 수명을 0으로 설정하는 방법을 사용
			쿠키의 수명은 쿠키를 저장하는 addCookie() 메소드를 호출하기 전에 삭제하려는 쿠키의 setMaxAge() 메소드 사용해서 처리
			형식) cookie객체변수.setMaxAge(시간); ==> 시간을 0으로 설정하면 바로 삭제, 음수로 지정하면 웹브라우저가 닫힐 때 삭제
		 */
		
		Cookie[] cookieArr = request.getCookies(); //전체 쿠키 정보 가져오기
		
		out.println("<html>");
		out.println("<head><meta charset='utf-8'><title>Cookie 연습</title></head>");
		out.println("<body>");
		out.println("<h3>저장된 Cookie 정보 삭제</h3><hr>");
		
		if(cookieArr == null || cookieArr.length == 0) {
			out.println("<h3>저장된 Cookie가 하나도 없습니다.</h3>");
		}else {
			//쿠키 배열에서 삭제할 쿠키 구하기 (보통 쿠키 key값으로 확인)
			for(Cookie cookie : cookieArr) {
				String key = cookie.getName(); //쿠키의 key값 구하기
				
				if("gender".equals(key)) { //삭제할 대상 찾기
					cookie.setMaxAge(0); //유지 시간을 0으로 설정
					response.addCookie(cookie); //유지 시간을 0으로 설정한 쿠키 저장
				}
			}
		}
		
		out.println("<a href='" + request.getContextPath() + "/basic/cookie/cookieTest.jsp'>쿠키 시작문서로 가기</a>");
		out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
