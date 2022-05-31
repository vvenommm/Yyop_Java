package kr.or.ddit.basic.cookie;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class cookieAdd
 */
@WebServlet("/cookieAdd.do")
public class CookieAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter(); //출력 스트림
		
		//Cookie 저장하는 방법
		
		/*
			1. Cookie 객체 생성 ==> '쿠키key값'과 '쿠키value값'은 문자열로 지정
				형식) Cookie cookie변수 = new Cookie("쿠키key값", "쿠키value값");
					==> '쿠키Value값'으로 한글을 사용할 경우 URLEncoder.encode() 메소드로 인코딩 후 저장
		 */
		Cookie nameCookie = new Cookie("name", URLEncoder.encode("후후리", "utf-8"));
		int age = 30;
//		Cookie  ageCookie = new Cookie("age", age+"");
		Cookie ageCookie = new Cookie("age", String.valueOf(age)); //String으로 형변환 필수
		Cookie genderCookie = new Cookie("gender", "female");
		
		/*
			2. 쿠키 속성 설정
				1) 쿠키변수.setPath("적용경로");		==> 지정한 경로와 그 하위 경로에서 사용 가능. 생략 시 쿠키 설정 시의 경로가 기본값
				2) 쿠키변수.setMaxAge(유지시간);		==> 단위 : 초. / -1:브라우저 종료 시까지 유지(기본값), 0: 쿠키 즉시 삭제
				3) 쿠키변수.setDomain("적용도메인명");  ==> 예) ".ddit.or.kr" ==> www.ddit.or.kr, www2.ddit.or.kr
				4) 쿠키변수.setSecure(보안여부);		==> true: 적용, false: 미적용
		
			3. Response 객체를 이용해 웹브라우저로 쿠키 전송 시 웹브라우저가 쿠키를 받아서 저장
				형식) response객체변수.addCookie(1번에서 만든 Cookie변수);
		 */
		response.addCookie(nameCookie);
		response.addCookie(ageCookie);
		response.addCookie(genderCookie);
		
		out.println("<html>");
		out.println("<head><meta charset='utf-8'><title>Cookie연습</title></head>");
		out.println("<body>");
		out.println("<h2>Cookie 데이터가 저장</h2><br><hr>");
		out.println("<a href='" + request.getContextPath() + "/basic/cookie/cookieTest.jsp'>쿠키 시작문서로 이동</a>");
		out.println("</body></html>");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
