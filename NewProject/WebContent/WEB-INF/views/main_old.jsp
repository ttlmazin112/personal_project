<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="css/default.css" rel="stylesheet" type="text/css">
<link href="css/front.css" rel="stylesheet" type="text/css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
	<style>
		body {overflow: hidden; margin: 0; padding:0; }
		#layout {position: relative; maring: auto; width: 500px; height:135px; display: flex; flex-warp: wrap; align-items: center; }
		#layout b {width: 20px; position: relative; z-index: 1; cursor:pointer; margin: auto 240px; }
		#banner  { position:absolute; z-index:0;  width:480px; height:135px;overflow:hidden; margin:auto 250px; }
		#banner img {position: absolute; width: 160px; height: 135px;  }
		#banner img:nth-child(1) { left:-160px; z-index:2; }
		#banner img:nth-child(2) { left:0px; }
		#banner img:nth-child(3) { left:160px; }
		#banner img:nth-child(4) { left:320px; }		
		#banner img:last-child { left:480px; }
	</style>

	<script>
	$(document).ready(function(){			
			var i=$("#banner img:first-child").attr('src').substr(-5,1);
			/****** 처음부터 자동 *****/
			var rolling=setInterval(frame, 1500);
			function frame() {
					
					$("#banner img").removeClass("firstImg").animate({left:'-=160px'});		
					i++;
					if( i==5) {i=1}		
					var appendImg="<img src='images/iicon"+i+".png'>";
					$("#banner img:nth-child(3)").addClass("firstImg");
					$("#banner").append(appendImg);
					if( $("#banner img:first-child").position().left<=-320 ) {  $("#banner img:first-child").remove()  }
			}			
			
			/****** 왼쪽버튼 클릭시 ******/
			$("#left").click(function(){
				clearInterval();
				$("#banner img").removeClass("firstImg").animate({left:'-=160px'});		
				i++;
				if( i==5) {i=1}		
				var appendImg="<img src='images/iicon"+i+".png'>";
				$("#banner img:nth-child(3)").addClass("firstImg");
				$("#banner").append(appendImg);
				if( $("#banner img:first-child").position().left<=-320 ) {  $("#banner img:first-child").remove()  }
			});
			
			/****** 오른쪽버튼 클릭시 ******/			
			$("#right").click(function(){				
				$("#banner img").removeClass("firstImg").animate({left:'+=160px'});	
				i=i-1;			
				if( i==0) {i=4}
				var prependImg="<img src='images/iicon"+i+".png'>";
				$("#banner img:nth-child(2)").addClass("firstImg");
				$("#banner").prepend(prependImg);
				if( $("#banner img:last-child").position().left>=640 ) {  $("#banner img:last-child").remove()  }
			});
	});
	</script>


<style type="text/css">
ul#portfolio{
list-style-type: none;
margin: 0px;
padding: 0px;

}
</style>
<script src="js/jquery-3.3.1.min.js"></script>
<script src="js/jquery.innerfade.js"></script>
<script>
// innerfade 제이쿼리 라이브러리
// https://medienfreunde.de/lab/innerfade/
// jquery.innerfade.zip 다운로드
$(function () {
	$('#portfolio').innerfade({
		animationtype: 'fade',
		speed: 'slow',
		timeout: 4000,
		type: 'random',
		containerheight: '220px'
	});
});



</script>
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
<!-- 헤더파일들어가는 곳 -->
<jsp:include page="/WEB-INF/views/inc/top.jsp"/>
<!-- 헤더파일들어가는 곳 -->
<!-- 메인이미지 들어가는곳 -->
<div class="clear"></div>
<div id="main_img"><!-- <img src="images/main_img3.jpg"
 width="971" height="282"> -->
  <ul id="portfolio"> 
	<li>
		<img src="images/main_1.jpg" width="971" height="283" alt="Good Guy bad Guy" />
	</li>
	<li>
		<img src="images/main_2.PNG" width="971" height="283" alt="Whizzkids" />
	</li> 
	<li>
		<img src="images/main_3.jpg" width="971" height="283" alt="Königin Mutter" />
	</li> 
	<li>
		<img src="images/main_4.jpg" width="971" height="283" alt="RT Hybride Archivierung" />
	</li> 
	<li>
		<img src="images/main_5.jpg" width="971" height="283" alt="TÜV SÜD Gruppe" />
	</li> 
</ul> 

 </div>
<!-- 메인이미지 들어가는곳 -->
<!-- 메인 콘텐츠 들어가는 곳 -->
<article id="front">
<!-- <div id="solution"> -->
<!-- <div id="hosting"> -->
<!-- <h3>고객 중심주의</h3> -->
<!-- <p>우리는 고객관점에서 판단하고  -->
<!-- 고객중심으로 결정하고 행동합니다.</p> -->
<!-- </div> -->
<!-- <div id="security"> -->
<!-- <h3>회원 우선주의</h3> -->
<!-- <p>우리는 어떤 상황에서도 회원을  -->
<!-- 우선순위에 두고 서비스를 제공합니다.</p> -->
<!-- </div> -->
<!-- <div id="payment"> -->
<!-- <h3>결과 중심주의</h3> -->
<!-- <p>우리는 언제나 투자수익의 결과를 -->
<!-- 내는데만 집중하고 서비스를 제공합니다. </p> -->
<!-- </div> -->
<!-- </div> -->
	<div id="layout">
	<b id="left">&lt;</b>
	<div id="banner">
		<img src="images/iicon1.png" width="160" height="135" ><img src="images/iicon2.png" width="160" height="135" class="firstImg"><img src="images/iicon3.png" width="160" height="135"><img src="images/iicon4.png" width="160" height="135"><img src="images/iicon1.png" width="160" height="135">
	</div>
	<b id="right">&gt;</b>
	</div>



<div class="clear"></div>
<div id="sec_news">
<h3><span class="orange">Security</span> News</h3>
<dl>
<dt>Vivamus id ligula....</dt>
<dd>Proin quis ante Proin quis anteProin 
quis anteProin quis anteProin quis anteProin 
quis ante......</dd>
</dl>
<dl>
<dt>Vivamus id ligula....</dt>
<dd>Proin quis ante Proin quis anteProin 
quis anteProin quis anteProin quis anteProin 
quis ante......</dd>
</dl>
</div>
<div id="news_notice">
<h3 class="brown">News &amp; Notice</h3>
<table>
<tr><td class="contxt"><a href="#">Vivans....</a></td>
    <td>2012.11.02</td></tr>
<tr><td class="contxt"><a href="#">Vivans....</a></td>
    <td>2012.11.02</td></tr>
<tr><td class="contxt"><a href="#">Vivans....</a></td>
    <td>2012.11.02</td></tr>
<tr><td class="contxt"><a href="#">Vivans....</a></td>
    <td>2012.11.02</td></tr>
<tr><td class="contxt"><a href="#">Vivans....</a></td>
    <td>2012.11.02</td></tr>
</table>
</div>
</article>
<!-- 메인 콘텐츠 들어가는 곳 -->
<div class="clear"></div>
<!-- 푸터 들어가는 곳 -->
<jsp:include page="/WEB-INF/views/inc/bottom.jsp"/>
<!-- 푸터 들어가는 곳 -->
</div>
</body>
</html>