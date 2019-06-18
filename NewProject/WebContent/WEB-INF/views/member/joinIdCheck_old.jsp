<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
<%
	String id = request.getParameter("userid");	

	boolean isDuplicated = (boolean) request.getAttribute("isDuplicated");

	if(isDuplicated){
		%>
		사용중인 아이디 입니다람쥐~ 다람쥐~.<br>
		<%	
	}else{
		%>
		사용가능한 아이디 입니다라이~ 다라이~.<br>
		<input type="button" value="ID사용" onclick="useIdAndClose()">
		<% 
	}
%>
<form action="joinIdCheck.do" method="get" name="fr">
	<input type="text" name="userid" value="<%=id %>">
	<input type="submit" value="중복체크">
</form>

</body>
</html>