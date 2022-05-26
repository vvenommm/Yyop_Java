<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Request 객체 연습용 Form</title>
</head>
<body>
<!-- 
	<form> 태그의 속성
	1) action => form에서 만들어진 데이터를 받아서 처리할 문서형 또는 서블릿 URL
	2) method => 전송방식(GET 또는 POST) ==> (기본값 : GET)
	3) enctype => 서버로 파일을 전송할 때 이 속성에 'multipart/form-data'라고 설정
	4) target => form의 데이터를 처리한 응답이 나타날 'frame명' 지정
	
 -->

<%-- 이 영역은 JSP 주석 영역 --%>

<%
	//이 영역은 JSP 문서에서 JAVA 명령을 사용할 수 있는 영역으로 '스크립트릿(Scriptlet)'이라고 한다.
	String name = "홍길동";
%>
<%--
	<%=변수명 또는 수식 %> => JSP에서 '변수'나 수식의 결과를 출력할 때 사용
--%>

<h2><%= name %> Request 연습용 Form => <%= 3 * 6 - 3 %></h2>

<!-- <form action="/webTest/requestTest01.do" method="get"> -->
<form action="/webTest/requestTest01.do" method="post">
<table border="1">
	<tr>
		<td>이름</td>
		<td><input type="text" name="username" size="10"></td>
	</tr>
	<tr>
		<td>직업</td>
		<td>
			<select name="job">
				<option value="무직">=무직=</option>
				<option value="회사원">=회사원=</option>
				<option value="전문직">=전문직=</option>
				<option value="학생">=학생=</option>
				<option value="무직">=무직=</option>
			</select>
		</td>
	</tr>
	<tr>
		<td>취미</td>
		<td>
			<input type="checkbox" name="hobby" value="여행">여행
			<input type="checkbox" name="hobby" value="요리">요리
			<input type="checkbox" name="hobby" value="산책">산책
			<input type="checkbox" name="hobby" value="사진">사진
			<input type="checkbox" name="hobby" value="노래">노래
		</td>
	</tr>
	<tr>
		<td colspan="2" stype="text-align:center;">
			<input type="submit" value="전송">
			<input type=reset" value="취소">
		</td>
	</tr>
</table>
		
</table>
</form>

</body>
</html>
