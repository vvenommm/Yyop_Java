package kr.or.ddit.basic.cookie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookieLoginServlet.do")
public class CookieLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter(); //출력 스트림
		
		//id 있는지 검사
		Cookie[] arr = request.getCookies();
		
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String check = request.getParameter("check");
		
		//쿠키 객체 생성
		Cookie idc = new Cookie("id", id);
		Cookie passc = new Cookie("pass", pass);
		
		//체크박스 눌려있으면
		if(check != null) {
			//쿠키 저장
			response.addCookie(idc);
		}else {
			//쿠키 삭제
			idc.setMaxAge(0);
			response.addCookie(idc);
		}
		
		if(idc.getValue().equals("test") && passc.getValue().equals("1234")) {
			response.sendRedirect(request.getContextPath()+ "/basic/cookie/cookieMain.jsp");
		}else {
			response.sendRedirect(request.getContextPath()+ "/basic/cookie/cookieLogin.jsp");
		}
//		out.println("<html>");
//		out.println("<head><meta charset='utf-8'><title>Cookie Count 연습</title></head>");
//		out.println("<body>");
//		out.println("<h2>Cookie 로그인</h2><br><hr>");
//		for (Cookie cookie : arr) {
//			if (arr != null) {
//				if("id".equals(cookie.getName())) {
//					String value = cookie.getValue();
//					if(id.equals(value)) {
//						
//					}
////					String name = cookie.getName();
////					value = cookie.getValue();
////					idc = new Cookie("id", value);
////				out.println("<h2>" + name + "</h2><br><hr>");
//				}
//				
//			
//				out.println("<h2> id : " + id + "</h2><br><hr>");
//				out.println("<h2> pass : " + pass + "</h2><br><hr>");
//				out.println("<h2> checked : " + checked + "</h2><br><hr>");
//
//				out.println("</body></html>");
////				if("id".equals(cookie.getName())) {
////					if(value == "test") {
////						
////					}
//			}
//		}
	}
				
				
		

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
