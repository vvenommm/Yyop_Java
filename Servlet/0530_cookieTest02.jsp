<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cookie Count 증가 초기화</title>
</head>
<body>
<a href="<%=request.getContextPath()%>/cookieCountServlet.do">Cookie Count 증가</a><br><br>
<a href="<%=request.getContextPath()%>/cookieCountDelServlet.do"">Cookie Count 초기화</a><br><br>
</body>
</html>
