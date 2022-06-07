<%@page import="kr.or.ddit.basic.fileupload.vo.FileInfoVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>File List.jsp입니다</title>
<style>
table{
	border : 3px double pink;
}
th{
	width: 100px;
	padding : 5px;
	text-align: center;
}
td{
	padding : 5px;
	text-align: center;
}
</style>
</head>
<body>
<%

	//Servlet에서 보내온 자료 받기
	List<FileInfoVO> fileList = (List<FileInfoVO>)request.getAttribute("fileList");
%>
<h3>!!전체 파일 목록!!</h3>
<br><hr><br>

<a href = "<%=request.getContextPath() %>/fileUpload.do">파일 업로드 하기</a><br>
<table border='1'>
	<tr>
		<th>번호</th><th>작성자</th><th>저장파일명</th><th>원래 파일명</th><th>파일크기</th><th>날짜</th><th>비고</th>
	</tr>
	<%
		if(fileList == null || fileList.size() == 0){
	%>
	<tr>
		<td colspan="7">저장된 파일 목록이 하~나도 없음</td>	
	</tr>		
	<%
		}else{
			for(FileInfoVO fvo: fileList){
	%>
				<tr>
					<td><%=fvo.getFile_no() %></td>
					<td><%=fvo.getFile_writer() %></td>
					<td><%=fvo.getSave_file_name() %></td>
					<td><%=fvo.getOrigin_file_name() %></td>
					<td><%=fvo.getFile_size() %></td>
					<td><%=fvo.getFile_date() %></td>
					<td><a href="<%=request.getContextPath()%>/FileDownload.do?fileno=<%=fvo.getFile_no()%>">Download</a></td>
				</tr>
		<%
				}
		%>

	<%
		}
	%>
</table>

</body>
</html>
