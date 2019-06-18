<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<style type="text/css">

</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/inc/MainHeader.jsp"/>

<h1>관리자 EMAIL 보내기</h1>

<form action="AdminMultiEmailAction.do" method="post" enctype="multipart/form-data">
받는사람 이메일주소: <input type="text" name="address" value="${emails}"><br> 
메일 제목: <input type="text" name="subject"><br>
첨부 파일: <input type="file" name="filename"><br>
메일 내용: <textarea rows="7" cols="40" name="content"></textarea><br>


<button type="submit">이메일 전송</button> 
</form>
</body>
</html>