package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletTest03
 */

//Servlet의 Lifecycle
@WebServlet("/servletTest03.do")
public class ServletTest03 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() throws ServletException {
		System.out.println("Servlet : " + this.getServletName() + "에서 init() 메소드 호출~");
	}
	
	
	@Override
	public void destroy() {
		//컨테이너에서 사용하던 메소드들 청소하는 메소드
		//이 파일 내용을 수정한 후 저장하면 서버가 알아서 재부팅을 하고 destroy() 메소드 호출
		System.out.println("Servlet : " + this.getServletName() + "에서 destroy() 메소드 호출~");
	}
	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Servlet : " + this.getServletName() + "에서 service() 메소드 시작!");
		
		//GET 방식과 POST 방식에 맞는 메소드 호출하기
		
		
		//방법1 ==> HttpServlet(부모클래스)의 service() 메소드로 위임하기. 서비스를 안 만들면 자동으로 이게 호출
		super.service(req, resp);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet() 메소드 시작~~~~~~");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html><head><meta charset='utf-8'></head> <body><h2>doGet() 메소드 처리 결과용</h2></body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost() 메소드 시작~~~~~~");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html><head><meta charset='utf-8'></head><body><h2>doPost() 메소드 처리 결과용</h2></body></html>");
	}

}
