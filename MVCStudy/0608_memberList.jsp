<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	List<MemberVO> list = (List<MemberVO>)request.getAttribute("memberList");
	for(MemberVO memberInfo : list){
%>
	<%=memberInfo.getMem_name() %><br>
<%		
	}
%>


</body>
</html>
