<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>탈퇴 화면</title>

<style type="text/css">
body{
margin-left: 500px
}
table {
	
	border: 3px solid skyblue;
}

td {
	border: 1px solid skyblue
}

#title {
	background-color: skyblue
}
</style>

<script type="text/javascript">
		// 비밀번호 미입력시 경고창
		function checkValue(){
			if(!document.userdel.password.value){
				alert("비밀번호를 입력하지 않았습니다.");
				return false;
			}
		}
	</script>

</head>
<body>
	<br>
	<br>
	<b><font size="6" color="gray">회원 탈퇴</font></b>
	<br>
	<br>
	<br>

	<form name="UserDeleteAction.do" method="post" action="UserDeleteAction.do"
		onsubmit="return checkValue()">

		<table>
			<tr>
				<td bgcolor="skyblue">비밀번호</td>
				<td><input type="password" name="password" maxlength="50"></td>
			</tr>
		</table>

		<br> <input type="button" value="취소"
			onclick="javascript:window.location='main.do'"> <input
			type="submit" value="탈퇴" />
	</form>
</body>
</html>