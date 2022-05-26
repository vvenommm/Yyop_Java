<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Request연습 Form</title>
</head>
<body>
<hr>
<h1>Request연습 Form(숫자 입력은 정수형으로 입력하~기!)</h1>
<form action = "/webTest/requestTest02.do" method="post">
<input type="text" name="no1">
<select name="op">
	<option value="+">+</option>
	<option value="-">-</option>
	<option value="*">*</option>
	<option value="/">/</option>
	<option value="%">%</option>
</select>
<input type="text" name="no2">
<input type="submit" value="확인">
</form>
</body>
</html>
