<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Servlet 요청 연습</title>
</head>
<body>
<h2>Servlet 요청 연습</h2>

<h3>GET방식 요청1 ==> 링크 방식</h3>
<a href="http://localhost/webTest/servletTest03.do">GET 방식 요청1</a>

<hr><hr>

<h3>GET방식 요청2 ==> Form의 submit 방식</h3>
<!-- 
	form 태그의 method 속성을 생략하거나 'get'으로 설정하면 'get방식으로 요청됨
 -->
<form action="http://localhost/webTest/servletTest03.do">
	<input type="submit" value="GET방식 요청2">
</form>

<hr><hr>

<h3>POST방식 요청 ==> Form의 submit 방식</h3>
<!-- 
	form 태그의 method 속성을 post로 설정하면 post 방식으로 요청 됨
 -->
<form action="http://localhost/webTest/servletTest03.do" method="post">
	<input type="submit" value="POST방식 요청">
</form>

</body>
</html>
