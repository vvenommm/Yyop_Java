<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Session Login JDBC</title>
</head>
<body>
<%
	String id = (String)session.getAttribute("id");
	if(id == null){
%>
<form action="<%=request.getContextPath()%>/JDBC_SessionLogin.do" method='post'>
<label>아이디</label>
<input type='text' name='id' placeholder='아이디를 입력하세요.'><br>
<label>비밀번호</label>
<input type='text' name='pass' placeholder='비밀번호를 입력하세요.'><br>
<input type="submit" value="로그인">
</form>
<%
	}else{
%>
<form action='<%=request.getContextPath()%>/JDBC_SessionLogout.do' method='post'>
<h3><%=id%>님 환영</h3><br>
<input type='submit' value='로그아웃'>
</form>
	
<%
}
%>
</body>
</html>
