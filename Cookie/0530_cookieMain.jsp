<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cookie Main</title>
<!--
	id 기억하기 체크박스를 체크한 후 login 버튼을 누르면 입력했던 id를 쿠키에 저장
	쿠키에 id값이 저장되어 있으면 id 입력 창에 저장된 id가 나타나도록.
	체크박스도 체크가 된 상태로 유지
	
	체크박스가 해제된 상태에서 login 버튼을 누르면 쿠키에 저장된 id를 삭제하고
	체크박스도 체크가 해제된 상태가 되도록.
	
	이 사이트에 회원은 id가 'test'이고 password는 1234.
	로그인에 성공하면 cookieMain.jsp로 이동, 실패하면 cookieLogin.jsp로 이동
-->
</head>
<body>
<h3>cookie 연습용 메인~~~</h3>
<a href="cookieLogin.jsp">Login 창으로 이동</a>
</body>
</html>
