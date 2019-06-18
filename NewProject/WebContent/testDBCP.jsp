<%@page import="java.sql.Connection"%>
<%@page import="dao.OracleDB"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	Connection con = OracleDB.getConnection();
	OracleDB.closeJDBC(con, null, null);
%>
DB연결 성공!<br>
</body>
</html>