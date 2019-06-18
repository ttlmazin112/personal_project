<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<!-- 본문메인이미지 -->
<div id="sub_img_member"></div>
<!-- 본문메인이미지 -->
<!-- 왼쪽메뉴 -->
<nav id="sub_menu">
<ul>
<li><a href="joinForm.do">회원가입</a></li>
<li><a href="#">Privacy policy</a></li>
</ul>
</nav>
<!-- 왼쪽메뉴 -->
<!-- 본문내용 -->
<article>
<h1>Login</h1>
<form action="loginProcess.do" id="join" method="post">
<fieldset>
<legend>Login Info</legend>
<label>User ID</label>
<input type="text" name="id"><br>
<label>Password</label>
<input type="password" name="pass"><br>
</fieldset>
<div class="clear"></div>
<div id="buttons">
<input type="submit" value="로그인" class="submit">
<input type="reset" value="초기화" class="cancel">
</div>
</form>
</article>
<!-- 본문내용 -->
<!-- 본문들어가는 곳 -->

<div class="clear"></div>
<!-- 푸터들어가는 곳 -->
<jsp:include page="/WEB-INF/views/inc/bottom.jsp"/>
<!-- 푸터들어가는 곳 -->
</div>
</body>
</html> --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/login.css" rel="stylesheet" type="text/css">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>Insert title here</title>
<!-- <script>
//AJAX (비동기 자바스크립트-XML/JSON 통신)

//자바의 병렬처리방법 : 스레드(분신술)
//자바스크립트의 병렬처리방법: 비동기처리(번호표 예약)

$(function () {
	$('button').click(function () {
		var str = $('form').serialize(); // 직렬화(데이터 문자열화)
		console.log(str);
		
		var userid = $("#userid").val();
		var passwd = $("#passwd").val();
		
		if (userid == ""){
			alert("아이디를 입력해주세요");
			$("#userid").focus();
			return;
		}
		
		 if (passwd == "") {
			 
             alert("비밀번호를 입력해주세요");

             $("#passwd").focus();
             return;
         }

		 var data = "userid=" + userid + "&passwd=" + passwd;


		
		
		$.ajax({
			async: true,  // 비동기방식:true, 동기방식:false
			url: "/WEB-INF/member/loginForm.do"
			data: data,
			method: 'POST',
			success: function (value) {
				setTimeout(function () {
					$('div').html('<h3>' + value + '</h3>');
				}, 3000);
			}
		}); // ajax()
		
		var objImg = '<img src="images/ajax-loader4.gif">';
		$('div').html(objImg);
		
	}); // click()
});
</script> -->

<!-- <script>


//AJAX (비동기 자바스크립트-XML/JSON 통신)

//자바의 병렬처리방법 : 스레드(분신술)
//자바스크립트의 병렬처리방법: 비동기처리(번호표 예약)

$(function () {
	$("#btn1").click(function javascript_onclick() {
		var str = $('form').serialize(); // 직렬화(데이터 문자열화)
		
		console.log(str);
		
	 	$.ajax({
			async: true,  // 비동기방식:true, 동기방식:false
			url: 'loginProcess.do',
			data: str,
			method: 'POST',
		/* 	success: function (result) {
				setTimeout(function () {
					$('div').html('<h3>' + result + '</h3>');
				}, 3000);
			} */
		}); // ajax()
		
		/* var objImg = '<img src="images/ajax-loader4.gif">'; */
		$('div').html(objImg); 
		
	}); // click()
});
</script> -->

</head>
<body>
<div class="wrapper fadeInDown">
  <div id="formContent">
    <!-- Tabs Titles -->

    <!-- Icon -->
    <div class="fadeIn first">
      <img src="https://t1.daumcdn.net/cfile/tistory/2078CF434D33BF1B1B" alt="User Icon" />
    </div>

    <!-- Login Form -->
    <form action="loginProcess.do" id="join" method="post" >
      <fieldset></fieldset>
      <input type="text" id="userid" name="id" class="fadeIn second" name="id" placeholder="아이디를 입력하세요">
      <input type="password" id="passwd" name="pass" class="fadeIn third" name="pw" placeholder="패스워드를 입력하세요">
      <input type="submit"  id="btn1" class="fadeIn fourth" value="투자의 세계로 접속"><br/>
      <input type="checkbox" id="keepLogin" name="keepLogin" value="yes">
      <label for="keepLogin">로그인 상태 유지</label>
      </fieldset>
    </form>
 

    <!-- Remind Passowrd -->
    <div id="formFooter">
      <a class="underlineHover" href="joinForm.do">회원가입</a>
    </div>

  </div>
</div>
</body>
</html>