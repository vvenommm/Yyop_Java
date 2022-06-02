package kr.or.ddit.basic.json;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JSONTest
 */
@WebServlet("/JSONTest.do")
public class JSONTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		//JSON 데이터로 응답 시 Content-Type 설정 방법
		response.setContentType("application/json; charset=utf-8");
		
		//JSP에서 보낸 ajax의 data 가져오기
		String choice = request.getParameter("choice");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
