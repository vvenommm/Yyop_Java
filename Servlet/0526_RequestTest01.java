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
@WebServlet("/responseTest01.do")
public class ResponseTest01 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
			forward 방식
			==> 특정 서블릿에 대한 요청을 다른 서블릿이나 JSP문서로 넘김
				이 때 매개변수도 같이 전송
			==> 클라이언트의 웹브라우저의 URL 주소는 처음 요청할 때의 값 변경 X
			==> 서버 내부에서만 접근 가능
			
			request.getRequestDispatcher("/이동할 페이지의 주소"); => 이동할 페이지의 주소 = ContexxtPath 이후의 경로로 기술
			
			원래 이동하려는 주소 => http://localhost/webTest/forwardTest.do
			
			String userName = request.getParameter("username");
			
			이동할 페이지로 현재 페이지에서 만들어진 데이터를 보내려면 request객체.setAttribute() 메소드 이용
			데이터를 받는 쪽: getAttribute() 메소드 사용
			형식) request객체.setAttribute("키값", 데이터값);
					'키값' => 문자열, '데이터값' => java의 모든 종류의 데이터 사용 가능
		 */
		
		request.setAttribute("tel", "010-0000-0000");
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/forwardTest.do");
		rd.forward(request, response);
		
		
		
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
