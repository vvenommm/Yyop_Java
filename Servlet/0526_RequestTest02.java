package kr.or.ddit.basic.reqNresp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestTest02
 */
@WebServlet("/requestTest02.do")
public class RequestTest02 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		int no1 = Integer.parseInt(request.getParameter("no1"));
		int no2 = Integer.parseInt(request.getParameter("no2"));
		
		String op = request.getParameter("op");
		double result = 0; //계산 결과 값
		boolean calcOk = true; //계산 성공 여부가 저장될 변수 (계산 성공:true, 실패:false)
		
		
		switch(op) {
		case "+": result = no1 + no2; break;
		case "-": result = no1 - no2; break;
		case "*": result = no1 * no2; break;
		case "/": result = no1 / no2; break;
		case "%": result = no1 % no2; break;
		}
		
//		String minus = request.getParameter("minus");
//		String multi = request.getParameter("multi");
//		String div = request.getParameter("div");
//		String mod = request.getParameter("mod");
		
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		out.println("<html><head><meta charset='utf-8'>");
		out.println("<title>Request 객체 연습용2</title></head>");
		out.println("<body>");
		out.println("<h2>RequestTest02.java</h2>");
		out.println("<h3>계산 결과</h3>");
		out.println("<hr>");
		out.println("<p>" + no1 + " " + op + " " + no2 + " = " + result + "</p>");
		out.printf("%d %s %d = %f<br>", no1, op, no2, result);
//		if(op.equals("+")) {
//			out.println((no1 + no2) + "</p>");
//		}else if(op.equals("-")) {
//			out.println((no1 - no2) + "</p>");
//		}else if(op.equals("*")) {
//			out.println((no1 * no2) + "</p>");
//		}else if(op.equals("/")) {
//			out.println(((double)no1 / (double)no2) + "</p>");
//		}else if(op.equals("%")) {
//			out.println((no1 % no2) + "</p>");
//		}
		
		switch(op) {
		case "+" : result = no1 + no2; break;
		case "-" : result = no1 - no2; break;
		case "*" : result = no1 * no2; break;
		case "/" :
			if(no2 != 0) {
				result = no1 / (double)no2;
			}else {
				calcOk = false;
			}
			break;
		case "%" :
			if(no2 != 0) {
				result = no1 % no2;
			}else {
				calcOk = false;
			}
			
			break;
		
		}
		
		out.println("<hr><hr>");
		out.println("<h3>Request 객체 연습 계산기</h3>");
		out.println("<hr>");
		out.println("<p>" + no1 + " " + op + " " + no2 + " = " + result + "</p>");
		out.printf("%d %s %d = ", no1, op, no2);
		if(calcOk == true) {
			out.println(result);
		}else {
			out.println("계산 불능 (0으로 나누기)");
		}

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
