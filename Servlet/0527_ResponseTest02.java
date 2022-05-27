package kr.or.ddit.basic.reqNresp;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ResponseTest01
 */
@WebServlet("/responseTest02.do")
public class ResponseTest02 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
			redirect 방식
			- 현재 페이지에서 다른 페이지로 제어를 넘길 때 사용 (이전 페이지에서 사용하던 매개변수를 직접 넘길 수 없음)
			- 응답 시 브라우저에게 '이동할 URL'을 전송하여 브라우저가 해당 URL로 이동시키는 방식
			- 이 때의 요청은 GET방식
			
			redirect 방식은 이전에 사용하던 Request객체를 유지하지 못함(브라우저가 새롭게 요청하기 때문에)
			
			redirect는 response객체의 sendRedirect() 메소드를 이용
			형식) response객체.sendRedirect("이동할 URL");
					이 때 이동할 url은 전체 url을 모두 기술
		 */
		
		response.sendRedirect(request.getContextPath() + "/redirectTest.do");
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
