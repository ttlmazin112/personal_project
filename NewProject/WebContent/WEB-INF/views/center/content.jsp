<%@page import="vo.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="css/default.css" rel="stylesheet" type="text/css">
<link href="css/subpage.css" rel="stylesheet" type="text/css">
<!--[if lt IE 9]>
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/IE9.js" type="text/javascript"></script>
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/ie7-squish.js" type="text/javascript"></script>
<script src="http://html5shim.googlecode.com/svn/trunk/html5.js" type="text/javascript"></script>
<![endif]-->
<!--[if IE 6]>
 <script src="script/DD_belatedPNG_0.0.8a.js"></script>
 <script>
   /* EXAMPLE */
   DD_belatedPNG.fix('#wrap');
   DD_belatedPNG.fix('#main_img');   

 </script>
 <![endif]-->
</head>
<body>
<div id="wrap">
<!-- 헤더들어가는 곳 -->
<jsp:include page="/WEB-INF/views/inc/top.jsp"/>
<!-- 헤더들어가는 곳 -->

<!-- 본문들어가는 곳 -->
<!-- 메인이미지 -->
<div id="sub_img_center"></div>
<!-- 메인이미지 -->

<!-- 왼쪽메뉴 -->
<nav id="sub_menu">
<ul>
<li><a href="boardList.do">자유게시판</a></li>
<li><a href="boardFileList.do">회원 매매일지</a></li>
<li><a href="GuestbookForm.do">방명록</a></li>
</ul>
</nav>
<!-- 왼쪽메뉴 -->

<!-- 게시판 -->
<article>
<h1>Notice Content</h1>
<%-- <%
//페이지번호 파라미터 가져오기 
String pageNum = request.getParameter("pageNum");
Board board = (Board) request.getAttribute("board");




%> --%>

<table id="notice">

<tr>
	<th>글번호</th><td>${requestScope.board.num}</td> 
	<th>조회수</th><td>${board.readcount}</td>
</tr>
<tr>
	<th>작성자</th><td>${board.name}</td>
	<th>작성일</th><td>${board.reg_date}</td>
</tr>
<tr><th>글제목</th>
<td colspan="3" class="left">${board.subject}</td></tr>
<tr>
	<th>글내용</th>
	<td colspan="3" class="left">${board.content}</td>
</tr>
</table>

<%--
  세션값 있으면(로그인 했으면)
  수정, 삭제, 답글쓰기 버튼 보이게 설정
 --%>

<div id="table_search">
<c:if test="${id ne null }">
	<input type="button" value="글수정" class="btn" onclick="location.href='boardModifyForm.do?num=${board.num }&pageNum=${param.pageNum }';">
	<input type="button" value="글삭제" class="btn" onclick="location.href='boardDeleteForm.do?num=${board.num }&pageNum=${param.pageNum }';">
	<input type="button" value="답글쓰기" class="btn" 
	onclick="location.href='boardReplyForm.do?re_ref=${board.re_ref }&re_lev=${board.re_lev }&re_seq=${board.re_seq }&pageNum=${param.pageNum }';">
</c:if>

<input type="button" value="글목록" class="btn" onclick="location.href='boardList.do?pageNum=${param.pageNum }';">
</div>


<div class="clear"></div>

</article>
<!-- 게시판 -->
<!-- 본문들어가는 곳 -->
<div class="clear"></div>
<!-- 푸터들어가는 곳 -->
<jsp:include page="/WEB-INF/views/inc/bottom.jsp"/>
<!-- 푸터들어가는 곳 -->
</div>
</body>
</html>