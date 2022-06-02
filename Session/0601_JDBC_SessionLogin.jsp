<%@page import="kr.or.ddit.basic.session.iBatis_MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Session Login iBatis</title>
</head>
<body>
<!-- 
		MYMEMBER 테이블에 저장된 회원ID와 Password로 로그인 처리를 하고,
		환영 메시지는 회원 이름이 출력 되도록 수정
 -->
<%
	//JSP 문서에서 세션은 'session'이라는 이름으로 이미 만들어져 있음
	iBatis_MemberVO loginVo = (iBatis_MemberVO)session.getAttribute("memVo");

// 	String id = (String)session.getAttribute("id");
	if(loginVo == null){
%>
<form action="<%=request.getContextPath()%>/iBatis_SessionLogin.do" method='post'>
<label>아이디</label>
<input type='text' name='id' placeholder='아이디를 입력하세요.'><br>
<label>비밀번호</label>
<input type='text' name='pass' placeholder='비밀번호를 입력하세요.'><br>
<input type="submit" value="로그인">
</form>
<%
	}else{
		String loginName = loginVo.getMem_name();
%>
<form action='<%=request.getContextPath()%>/iBatis_SessionLogin.do' method='get'>
<h3><%=loginName%>님 환영</h3><br>
<%-- <a href "<%=request.getContextPath()%>/iBatis_SessionLogout.do"></a> --%> <!-- a로 링크 걸어서 이동하면  get방식으로 전송 됨 -->
<input type='submit' value='로그아웃'>
</form>
	
<%
}
%>
</body>
</html>
