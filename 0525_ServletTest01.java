package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
	서블릿이란? -> 컨테이너에 의해 관리되는 자바 기반 웹 컴포넌트로, 동적인 웹 컨텐츠 생성을 가능하게 하는 기술
	
	URL 주소 : http://localhost:80/webTest/servletTest01.do
	- http				=> 프로토콜
	- localhost			=> 컴퓨터이름(도메인명) 또는 IP주소
	- 80				=> 포트번호(80번일 경우 생략 가능)
	- /webTest			=> 컨텍스트 패스 (Context path). 보통 프로젝트명으로 설정
	- /servletTest01.do => 서블릿 요청 URL 패턴
	
	
 */
//Servelet 클래스는 HttpServlet을 상속해서 작성
//이 예제는 배포서술자(Deployment Descriptor, DD -> web.xml)를 이용해서 실행할 Servlet 예제
public class ServletTest01 extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	/*
		이 곳에서는 대부분 service() 메소드 또는 doGet() 메소드나 doPost() 메소드를 재정의해서 작성
		
		doGet() 메소드나 doPost() 메소드는 service() 메소드에서 전송방식 (get, post)에 따라 자동으로 호출
		
		HttpServletRequest 객체 -> 서비스 요청에 관련된 정보 및 메소드를 관리하는 객체
		HttpServletResponse 객체 -> 서비스 응답에 관련된 정보 및 메소드를 관리하는 객체
	 */
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setCharacterEncoding("utf-8"); //응답 문서의 인코딩 방식 설정
		response.setContentType("text/html; charset=utf-8");
		
		//처리한 내용을 응답으로 보내기 위한 스트림 객체(PrintWriter객체)를 생성
		PrintWriter out = response.getWriter();
		
		//처리한 내용 출력
		//방법1) append() 메소드 이용
		out.append("<!DOCTYPE html>")
		.append("<html>")
		.append("<head>")
		.append("<meta charset='utf-8'")
		.append("</head>")
		.append("<body>")
		.append("<h2 style='text-align:center;'>")
		.append("안녕하세요. 첫번째 서블릿 프로그램입니다.<br>")
		.append("ContextPath : " + request.getContextPath())
		.append("</h2>")
		.append("</body>")
		.append("</html>");
	}
	
}
