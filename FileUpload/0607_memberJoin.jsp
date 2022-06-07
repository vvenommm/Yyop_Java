<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 입력 폼</title>
</head>
<body>

<form action="<%=request.getContextPath()%>/memberJoin.do" method='post'>
	<table border="1">
		<tr>
			<td>
				<label>회원아이디</label>
			</td>
			<td>
				<input type="text" id="id" name="id" placeholder="아이디를 입력해주세요.">
				<button formaction="<%=request.getContextPath()%>/memberJoin.do">중복확인</button>
			</td>
		</tr>
		<tr>
			<td>
				<label>비밀번호</label>
			</td>
			<td>
				<input type="password" id="pw"  name="pw"  placeholder="비밀번호를 입력해주세요."><br>
			</td>
		</tr>
		<tr>
			<td>
				<label>비밀번호 확인</label>
			</td>
			<td>
				<input type="password" id="pwCF" name="pwCF"  placeholder="비밀번호를 다시 입력해주세요.">
			</td>
		</tr>
		<tr>
			<td>
				<label>이름</label>
			</td>
			<td>
				<input type="text" id="name" name="name"  placeholder="이름을 입력해주세요.">
			</td>
		</tr>
		<tr>
			<td>
				<label>연락처</label>
			</td>
			<td>
				<input type="text" id="name" name="name"  placeholder="연락처를 입력해주세요.">
			</td>
		</tr>
		<tr>
			<td>
				<label>주소</label>
			</td>
			<td>
				<input type="text" id="name" name="name"  placeholder="주소를 입력해주세요.">
			</td>
		</tr>
		<tr>
			<td>
				<label>프로필 사진</label>
			</td>
			<td>
				<button id="prof" name="prof"  formaction="">사진첨부</button>
			</td>
		</tr>
	</table>
</form>

</body>
</html>
