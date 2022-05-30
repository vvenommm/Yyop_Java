package kr.or.ddit.basic.cookie;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CookieRead
 */
@WebServlet("/cookieRead.do")
public class CookieRead extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//쿠키 정보 읽어오기
		
		/*
			1. 전체 쿠키 정보를 Request 객체를 통해 수신 ==> 가져온 쿠키 정보들을 배열에 저장
				형식) Cookie[] 쿠키배열변수 = Request객체변수.getCookies();
		 */
		Cookie[] cookieArr = request.getCookies();
		
		out.println("<html>");
		out.println("<head><meta charset='utf-8'><title>Cookie 연습</title></head>");
		out.println("<body>");
		out.println("<h3>저장된 Cookie 정보 확인</h3><hr>");
		
		if(cookieArr == null || cookieArr.length == 0) {
			out.println("<h3>저장된 Cookie가 하나도 없습니다.</h3>");
		}
		
		// 2. 쿠키 배열에서 해당 쿠키 정보 구하기
		for(Cookie cookie : cookieArr) {
			String key = cookie.getName(); //쿠키의 key값 구하기
			
			//cookie.getValue() ==> 쿠키의 value값 구하기
			String value = URLDecoder.decode(cookie.getValue(), "utf-8");
			
			out.print("쿠키 key값 : " + key + "<br>");
			out.println("쿠키 value값 : " + value + "<hr>");
		}
		out.println("<a href='" + request.getContextPath() + "/basic/cookie/cookieTest.jsp'>쿠키 시작문서로 이동</a>");
		out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
