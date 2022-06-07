<%@page import="kr.or.ddit.basic.fileupload.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 목록 보기</title>
</head>
<body>
<%
	//서블릿에서 보낸 자료 받기
	List<MemberVO> memberList = (List<MemberVO>)request.getAttribute("memberList");

%>

<h3>회원 목록 보기</h3>

<table border="1">
	<tr>
		<th colspan="5"><a href="memberJoin.jsp"><button>회원추가</button></a></th>
	</tr>
	<tr>
		<th>아이디</th>
		<th>비밀번호</th>
		<th>이름</th>
		<th>전화</th>
		<th>주소</th>
	</tr>
<%
	if(memberList != null && memberList.size() > 0){
		for(MemberVO vo : memberList){
%>
	<tr>
		<td><%=vo.getMem_id()%></td>
		<td><%=vo.getMem_pass()%></td>
		<td><%=vo.getMem_name()%></td>
		<td><%=vo.getMem_tel()%></td>
		<td><%=vo.getMem_addr()%></td>
	</tr>
<%
		}
	}else{
%>
	<tr><td colspan="5">회원 정보가 없습니다.</td></tr>
<%
	}
%>	
</table>


</body>
</html>
