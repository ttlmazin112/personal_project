<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%
//세션값 가져오기 
String id =(String)session.getAttribute("id");
%>  

<html>
<head>
<style type="text/css">
.black{color: orange;
		font-weight: bolder;
		text-align: center;
		font-size: large;
}
body {
	background-image: url("images/guest.jpg");
	background-size: cover;
}
</style>
<%

if(id != null){//id != null
		%><div class="black"><%=id %>님 반가워요~</div> <%
	
}

%>

	<title>상단 영역</title>
	
	<link rel="stylesheet" href="css/bootstrap.min.css">
	
	<style type="text/css">
		#wrap{
			text-align: center;
			width: 800px;
			height: 150px;
		}
	</style>
	
	<script type="text/javascript">
		
		function changeView(value){
			
			if(value == "0") // HOME 버튼 클릭시 첫화면으로 이동
			{
				location.href="main.do";
			}
			else if(value == "1") // 로그인 버튼 클릭시 로그인 화면으로 이동
			{
				location.href="loginForm.do";
			}
			else if(value == "2") // 회원가입 버튼 클릭시 회원가입 화면으로 이동
			{
				location.href="joinForm.do";
			}
			else if(value == "3") // 로그아웃 버튼 클릭시 로그아웃 처리
			{
				location.href="logout.do";
			}
			else if(value == "4") // 내정보 버튼 클릭시 회원정보 보여주는 화면으로 이동
			{
				location.href="MemberInfoAction.do";
			}
			else if(value == "5")
			{
				location.href="member_list.do";
			}
			else if(value == "6")
			{
				location.href="boardList.do";
			}
			else if(value == "7")
			{	
				location.href="GuestbookListAction.do";
			}else if (value == "8") {
				location.href="userInfo.do";
			}
		}
	</script>
	
</head>
<body>
	<div id = "wrap">
		<p>
			<button class="btn btn-success" onclick="changeView(0)">HOME</button>

		<!-- // 로그인 안되었을 경우 - 로그인, 회원가입 버튼을 보여준다. -->
		<c:if test="${sessionScope.id==null}">
			<button id="loginBtn" name="id" class="btn btn-primary" onclick="changeView(1)">로그인</button>
			<button id="joinBtn" class="btn btn-primary" onclick="changeView(2)">회원가입</button>
		</c:if>
	
		<!-- // 로그인 되었을 경우 - 로그아웃, 내정보 버튼을 보여준다. -->
		<c:if test="${sessionScope.id!=null}">
			<button id="logoutBtn" class="btn btn-primary" onclick="changeView(3)">로그아웃</button>
			<button id="updateBtn" class="btn btn-primary" onclick="changeView(8)">내정보</button>

		</c:if>
		
			<button id="joinBtn" class="btn btn-info" onclick="changeView(6)">게시판</button>
			<button id="joinBtn" class="btn btn-info" onclick="changeView(7)">방명록</button>
			
		<!--  관리자 로그인 -->
		<c:if test="${sessionScope.id !=null && sessionScope.id=='admin'}">
			<button id="memberViewBtn" class="btn btn-warning" onclick="changeView(5)">회원보기</button>
			<button id="updateBtn" class="btn btn-primary" onclick="changeView(4)">통계</button>
			
		</c:if>
		<br>
			<b><font size="10" color="gray" >방명록</font></b>	
		</p>
	</div>
</body>
</html>