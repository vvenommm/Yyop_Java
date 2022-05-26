package kr.or.ddit.basic.reqNresp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestTest01
 */
@WebServlet("/requestTest01.do")
public class RequestTest01 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//post방식으로 전달되는 데이터의 인코딩 방식을 설정
		request.setCharacterEncoding("utf-8");
		
		//클라이언트에서 보낸 데이터를 받아서 처리하기(Request 객체 관련 데이터를 저장해서 받아옴)
		
		//request객체.getParameter("변수 명"); => 해당 변수에 설정된 값(항상 String 타입)을 가져온다.
		String userName = request.getParameter("username");
		String job = request.getParameter("job");
		
		//request객체.getParameterValues("변수 명"); => 같은 것이 여럿일 경우 사용하며 값의 자료형은 String[].
		String[] hobbies = request.getParameterValues("hobby");
		
		//출력
		response.setCharacterEncoding("utf-8"); //인코딩
		response.setContentType("text/html; charset=utf-8"); //출력 형식
		
		PrintWriter out = response.getWriter(); //출력 스트림
		out.println("<html><head><meta charset='utf-8'>");
		out.println("<title>Request 객체 연습용</title></head>");
		out.println("<body>");
		out.println("<h3>Request 테스트 결과 - 1</h3>");
		
		out.println("<hr>");
		
		out.println("<table border='1'>");
		out.println("<tr><td>이름</td>");
		out.println("<td>" + userName + "</td></tr>");

		out.println("<tr><td>직업</td>");
		out.println("<td>" + job + "</td></tr>");
		
		out.println("<tr><td>취미</td>");
		out.println("<td>");
		if(hobbies != null) {
			//배열 크기만큼 루프를 돌면서 값을 출력한다.
			for(int i = 0; i < hobbies.length; i++) {
				out.println(hobbies[i] + "<br>");
			}
			out.println("<hr>");
			
			//향상 for문 사용
			for(String h : hobbies) {
				out.println(h + "<br>");
			}
		}
		out.println("</td></tr>");
		out.println("</body></html>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
