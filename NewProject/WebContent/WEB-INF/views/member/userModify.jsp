<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
<head>

<title>회원정보 수정화면</title>

<style type="text/css">
body{
		margin-left: 750px;
			
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
	
// 비밀번호 입력여부 체크
function checkValue() {
	if (!document.userInfo.password.value) {
			alert("비밀번호를 입력하세요.");
			return false;
		}
	}
</script>

</head>
<body onload="init()">

	<br>
	<br>
	<b><font size="6" color="gray">회원정보 수정</font></b>
	<br>
	<br>
	<br>
	<!-- 회원정보를 가져와 member 변수에 담는다. -->
	<c:set var="member" value="${requestScope.memberInfo}" />

	<!-- 입력한 값을 전송하기 위해 form 태그를 사용한다 -->
	<!-- 값(파라미터) 전송은 POST 방식 -->
	<form method="post" action="MemberModifyAction.do" name="userInfo"
	onsubmit="return checkValue()">

		<table>
			<tr>
				<td id="title">아이디</td>
				<td id="title">${member.id}</td>
			</tr>
			<tr>
				<td id="title">비밀번호</td>
				<td><input type="password" name="password" maxlength="50"
					value="${member.password}"></td>
			</tr>
		</table>
		<br>
		<br>
		<table>

			<tr>
				<td id="title">이름</td>
				<td><input type="text" name="name" maxlength="50"
					value="${member.name}"></td>
			</tr>

			<tr>
				<td id="title">성별</td>
				<td>${member.gender}</td>
			</tr>

			<tr>
				<td id="title">생일</td>
				<td>${member.birthday}</td>
			</tr>

			<tr>
				<td id="title">이메일</td>
				<td><input type="text" name="email" maxlength="50"
					value="${member.email}"> </td>
			</tr>

			<tr>
				<td id="title">휴대전화</td>
				<td><input type="text" name="mtel" value="${member.mtel}" />
				</td>
			</tr>
			<tr>
				<td id="title">주소</td>
				<td><input type="text" size="50" name="address"
					value="${member.address}" /></td>
			</tr>
		</table>
		<br>
		<br> <input type="button" value="취소"
			onclick="javascript:window.location='main.do'"> <input
			type="submit" value="수정" />
	</form>

</body>
</html>