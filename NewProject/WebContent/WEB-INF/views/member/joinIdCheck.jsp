<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function useIdAndClose() {
	// 현재창을 띄운 부모창의 참조는 window.opener
	opener.document.frm.id.value = document.fr.userid.value;	  
	
	// 창닫기 window.close(); <- 생략가능 close();만 적어도됨
	window.close();
}
</script>
</head>
<body>
<%--
el ${}에서 Scope명 생략시 아래 순서대로 검색함
pageScope -> requestScope -> sessionScop -> applicationScop
 --%>
<c:choose>
	<c:when test="${requestScope.isDuplicated }">
	사용중인 아이디 입니다람쥐~ 다람쥐~.<br>
	</c:when>
	<c:otherwise>
	사용가능한 아이디 입니다라이~ 다라이~.<br>
		<input type="button" value="ID사용" onclick="useIdAndClose()">
	</c:otherwise>
</c:choose>

<form action="joinIdCheck.do" method="get" name="fr">
	<input type="text" name="userid" value="${param.userid}">
	<input type="submit" value="중복체크">
</form>

</body>
</html>