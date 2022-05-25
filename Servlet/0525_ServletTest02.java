package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
	이 예제는 annotation(@WebServlet)을 이용하여 Servlet을 설정, 처리하는 예제
	@WebServlet annotation Servlet 버전 3.0 이상에서 사용 가능
	
	- @WebServlet 애노테이션의 속성
	1) name : 서블릿 이름을 설정 (기본값 : 빈문자열(""))
	2) urlPatterns : 서블릿의 URL 패턴 목록을 설정 (기본값 : 빈배열({})
		예) urlPatterns="/url1" 또는 urlPatterns={"/url1"} ==> 패턴이 하나일 때
		예) urlPatterns={"/url1", "/url2", "url3", ....} ==>패턴이 2개 이상일 때
	3) value : urlPatterns와 동일
	4) description : 주석(설명글) 설정
 */

@WebServlet(urlPatterns = {"/servletTest02.ddit"}, description = "annotation을 이용한 서블릿 연습")
public class ServletTest02 extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	//doGet() 메소드 ==> GET방식으로 요청할 때 처리되는 메소드
	@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			response.setCharacterEncoding("utf-8"); //응답 문서의 인코딩 방식 설정
			response.setContentType("text/html; charset=utf-8");
			
			//처리한 내용을 응답으로 보내기 위한 스트림 객체(PrintWriter객체)를 생성
			PrintWriter out = response.getWriter();
			
			//처리한 내용 출력
			//방법2) print() 메소드 또는 println() 메소드 이용
			out.println("<!DOCTYPE html>");
			out.println("<html><head><meta charset='utf-8'>");
			out.println("<title>두번째 서블릿</title></head>");
			out.println("<body>");
			out.println("<h2 style='text-align:center; color:blue;'>");
			out.println("두번째 Servlet입니다. (@WebServlet annotation을 이용한 서블릿)");
			out.println("</h2>");
			out.println("</body></html>");
			
			
	}
	
	//doPost() 메소드 ==> POST 방식으로 요청할 때 처리되는 메소드
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
	}
	
	
	
}
