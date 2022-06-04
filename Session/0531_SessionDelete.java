package kr.or.ddit.basic.session;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.REUtil;

/**
 * Servlet implementation class SessionAdd
 */
@WebServlet("/SessionDelete.do")
public class SessionDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();

		//session 삭제하기
		
		/*
			1. removeAttribute()메서드로 개별적인 Session값 삭제하기
				형식) session객체.removeAttrivute("key값");
					==> Session객체는 삭제되지 않고 해당 'key값'에 설정된 session값만 삭제
					==> 지정한 'key값'과 일치하는 섹션값 삭제
					session.removeAttribute("userName");

			2. invalidate()메서드로 Session객체 삭제하기
				형식) session객체.invalidate();
					==> Session객체가 삭제
			
			3. invalidate()메서드로 Session객체 삭제하기
				형식) session객체.invalidate();
					==> Session객체가 삭제
		 */
		
		//생성하기
		HttpSession session = request.getSession();
		
		session.removeAttribute("testSession");
		
		session.invalidate();
		out.println("<html><head><meta charset='utf-8'>");
		out.println("<title>Session 삭제</title></head>");
		out.println("<body>");
		out.println("<h2>Session 정보 삭제하기</h2>");
		
		out.println("<a href = '" +request.getContextPath()+ "/session/sessionTest.jsp'>시작문서로 가기</a>");
		out.println("</body><html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
