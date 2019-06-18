<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원정보수정</h1>
	<form action="MemberUpdateProcess.do" method="get">
	
		<p>
			<label for="uId">I D : ${member.id}</label> <input type="hidden"
				name="id" value="${member.id}">
		</p>
		<p>
			<label for="uPwd">password :</label> <input type="text" id="uPwd"
				name="password" size="20" value="${member.password}">
		</p>
		<p>
			<label for="uName">name :</label> <input type="text" id="uName"
				name="name" size="20" value="${member.name}">
		</p>
		<p>
			<label for="uEmail">email :</label> <input type="text" id="uEmail"
				name="email" size="20" value="${member.email}">
		</p>
		<p>
			<label for="uPhone">address :</label> <input type="text" id="uPhone"
				name="address" size="20" value="${member.address}">
		</p>
		<p>
			<label for="uPhone">mtel :</label> <input type="text" id="uPhone"
				name="mtel" size="20" value="${member.mtel}">
		</p>
		<p>
			<c:choose>
				<c:when test="${mem.admin=='0'}">
					<label for="admin">관리자</label>
					<input type="radio" id="admin" name="admin" value="0" checked>
					<label for="normal">일반</label>
					<input type="radio" id="normal" name="admin" value="1">
				</c:when>
				<c:otherwise>
					<label for="admin">관리자</label>
					<input type="radio" id="admin" name="admin" value="0">
					<label for="normal">일반</label>
					<input type="radio" id="normal" name="admin" value="1" checked>
				</c:otherwise>
			</c:choose>
		</p>
		<input type="submit" value="제출">
	</form>
</body>


</html>