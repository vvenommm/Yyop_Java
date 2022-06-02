<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSON Test</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(function(){
	$("#strBtn").on('click', function(){
		$.ajax({
			url : "<%=request.getContextPath()%>/JSONTest.do",
			type : "POST",
			data : "choice=str",
// 			data : {"choice" : "str"},
			success : function(data){
				
			},
			error : function(xhr){
				alert(xhr.status);
			},
			dataType : 'json' //응답으로 온 데이터의 타입
			
		})
	})
})
</script>
</head>
<body>

<form>
	<input type="button" id="strBtn" value="문자열">
	<input type="button" id="arrBtn" value="배  열">
	<input type="button" id="objBtn" value="객  체">
	<input type="button" id="listBtn" value="리스트">
	<input type="button" id="mapBtn" value="Map">
</form>

<hr>

<div id="result">

</div>

</body>
</html>
