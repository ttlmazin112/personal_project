<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html>
<html>
<head>
<title>결과 페이지</title>
</head>
<body>

<input type="text">
	<c:set var="msg" value="${sessionScope.msg}" scope="session" />
	<c:choose>
		<c:when test="${msg!=null && msg=='0'}">
			<font size='6'>회원정보가 수정되었습니다.</font>
			<c:remove var="msg" scope="session"></c:remove>
		</c:when>
		<c:when test="${msg!=null && msg=='1'}">
			<font size='6'>회원가입을 축하드립니다.</font>
			<c:remove var="msg" scope="session"></c:remove>
		</c:when>
		<c:otherwise>
			<font size='6'>회원정보가 삭제되었습니다.</font>
		</c:otherwise>
	</c:choose>

	<br>
	<br>
	<input type="button" value="메인으로"
		onclick="javascript:window.location='main.do'" />

</body>
</html>