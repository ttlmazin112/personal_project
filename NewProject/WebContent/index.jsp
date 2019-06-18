<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<%--
http://localhost:80/funweb-mvc 요청오면
http://localhost:80/funweb-mvc/main.do 로 리다이렉트 시킨다. 
 --%>    
<%-- <% response.sendRedirect("main.do"); %> --%>
<c:redirect url="main.do"/>