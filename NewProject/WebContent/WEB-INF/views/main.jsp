<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
		<title></title>
		<meta charset="utf-8">
		<link rel="stylesheet" href="css/reset.css" type="text/css" media="all">
		<link rel="stylesheet" href="css/layout.css" type="text/css" media="all">
		<link rel="stylesheet" href="css/prettyPhoto.css" type="text/css" media="all">
		<link rel="stylesheet" href="css/style.css" type="text/css" media="all">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
	<style>
		body {overflow: hidden; margin: 0; padding:0; }
		#layout {position: absolute; margin: auto auto auto auto; width: 500px; height:135px; display: flex; flex-warp: wrap; align-items: center; }
		#layout b {width: 20px; position: relative; z-index: 1; cursor:pointer; margin: auto 240px; }
		#banner  { position:absolute; z-index:0;  width:480px; height:135px;overflow:hidden; margin:auto 250px; }
		#banner img {position: absolute; width: 160px; height: 135px;  }
		#banner img:nth-child(1) { left:-160px; z-index:2; }
		#banner img:nth-child(2) { left:0px; }
		#banner img:nth-child(3) { left:160px; }
		#banner img:nth-child(4) { left:320px; }		
		#banner img:last-child { left:480px; }
		section{margin: 200px 200px 200px}
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
	
		<script type="text/javascript" src="js/jquery-1.6.js" ></script>
		<script type="text/javascript" src="js/cufon-yui.js"></script>
		<script type="text/javascript" src="js/cufon-replace.js"></script>
  		<script type="text/javascript" src="js/Ubuntu_400.font.js"></script>
  		<script type="text/javascript" src="js/Ubuntu_700.font.js"></script>
		<script type="text/javascript" src="js/bgSlider.js" ></script>
		<script type="text/javascript" src="js/script.js" ></script>
		<script type="text/javascript" src="js/jquery.easing.1.3.js"></script>
		<script type="text/javascript" src="js/bg.js" ></script>
		<script type="text/javascript" src="js/tabs.js"></script>
		<script type="text/javascript" src="js/jquery.prettyPhoto.js"></script>
		
	
		<!--[if lt IE 9]>
			<script type="text/javascript" src="js/html5.js"></script>
		<![endif]-->
		<!--[if lt IE 7]>
			<div style='clear:both;text-align:center;position:relative'>
				<a href="http://www.microsoft.com/windows/internet-explorer/default.aspx?ocid=ie6_countdown_bannercode"><img src="http://storage.ie6countdown.com/assets/100/images/banners/warning_bar_0000_us.jpg" border="0"  alt="" /></a>
			</div>
		<![endif]-->
	</head>
	<body id="page1">


		<div class="spinner"></div>
		<div id="bgSlider"></div>
		<div class="extra">
		<jsp:include page="/WEB-INF/views/inc/MainHeader.jsp"/>
			<div class="main">
				<div class="box">
<!-- header -->
					<header>
						
						<h1><a href="main.do" id="logo">Thom Sander Photographer’s Portfolio</a></h1>
						<nav>
							<ul id="menu">
								<li><a href="welcome.do"><span></span><strong>CEO</strong></a></li>
								<li><a href="investment.do"><span></span><strong>performance</strong></a></li>
								<li><a href="book.do"><span></span><strong>Book</strong></a></li>
								<li><a href="apiMap.do"><span></span>Company Location</a></li>
								
							</ul>
						</nav>
					</header>
				<section>
					<div id="layout">
						<b id="left">&lt;</b>
						<div id="banner">
							<img src="images/iicon1.png" width="160" height="135"><img
								src="images/iicon2.png" width="160" height="135"
								class="firstImg"><img src="images/iicon3.png" width="160"
								height="135"><img src="images/iicon4.png" width="160"
								height="135"><img src="images/iicon1.png" width="160"
								height="135">
						</div>
						<b id="right">&gt;</b>
					</div>
				</section>

				<!--content -->
				
<!-- / content -->
				</div>
			</div>
			<div class="block"></div>
		</div>
		<div class="bg1">
			<div class="main">
<!-- footer -->
<jsp:include page="/WEB-INF/views/inc/footer.jsp"/>
<!-- / footer-->
			</div>
		</div>
		<script type="text/javascript"> Cufon.now(); </script>
		<script>
		$(window).load(function() {
			$('.spinner').fadeOut();
			$('body').css({overflow:'inherit'})
		})
		</script> 
	</body>
</html>