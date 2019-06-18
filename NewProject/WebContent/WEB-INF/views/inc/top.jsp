<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//세션값 가져오기 
String id =(String)session.getAttribute("id");
%>    
    
<header>
<div id="login">
<%

if(id == null){
	%><a href="loginForm.do">로그인</a><%
			
}else{//id != null
		%><%=id %>님 반가워요~ <a href="logout.do">로그아웃</a><%
	
}

%>

| <a href="joinForm.do">회원가입</a>
</div>
<div class="clear"></div>
<!-- 로고들어가는 곳 -->
<div id="logo"><a href="main.do"><img src="images/logo.png" width="100" height="100" style="margin-top: -50px" alt="Fun Web"></a></div>
<!-- 로고들어가는 곳 -->
<nav id="top_menu">
<ul>
	<li><a href="main.do">HOME</a></li>
	<li><a href="welcome.do">COMPANY</a></li>
	<li><a href="investment.do">INVESTMENT</a></li>
	<li><a href="boardList.do">CUSTOMER CENTER</a></li>
	<li><a href="#">CONTACT US</a></li>
</ul>
</nav>
</header>