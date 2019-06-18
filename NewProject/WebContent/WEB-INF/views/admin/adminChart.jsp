<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en"><!--  boardpage -->
<head>
<title>Potal &mdash; Administrator mode</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link
	href="https://fonts.googleapis.com/css?family=Quicksand:300,400,500,700,900"
	rel="stylesheet">
<link rel="stylesheet" href="fonts/icomoon/style.css">

<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/magnific-popup.css">
<link rel="stylesheet" href="css/jquery-ui.css">
<link rel="stylesheet" href="css/owl.carousel.min.css">
<link rel="stylesheet" href="css/owl.theme.default.min.css">

<link rel="stylesheet" href="css/bootstrap-datepicker.css">

<link rel="stylesheet" href="fonts/flaticon/font/flaticon.css">

<link rel="stylesheet" href="css/aos.css">

<link rel="stylesheet" href="css/style.css">

<link rel="stylesheet" href="css/custom.css">

<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<!-- bootstrapBoard -->

<script src="js/jquery-3.3.1.min.js"></script>
<script src="https://www.gstatic.com/charts/loader.js"></script>
<script>
google.charts.load('current', {packages: ['corechart']});

$(document).ready(function() {
	
	google.charts.setOnLoadCallback(function () {
		$.ajax({
			url: 'boardChartMonth.do',
			success: function(result) {
				console.log(result);
				monthChart(result);
			}
		});
		$.ajax({
			url: 'boardChartData.do',
			success: function(result) {
				console.log(result);
				columnChart(result);
			}
		});
		$.ajax({
			url: 'AdminChartId.do',  
			success: function(result) {
				console.log(result);
				pieChart(result);
			}
		});
	});
});

function monthChart(arrayList) {
	var dataTable = google.visualization.arrayToDataTable(arrayList);
	var options = {
			title: '최근 6개월간 게시글 수',
			/*
			hAxis: {
				title: '월',
				titleTextStyle: {
					color: 'red'
				}
			}
			*/
			chartArea: {width:"80%"},
			height: 500,
			legend: { position: 'bottom' },
			vAxis: { minValue: 0 }
	};
	var objDiv = document.getElementById('month_chart');
	var chart = new google.visualization.LineChart(objDiv);
	chart.draw(dataTable, options);
	/*
	var colSelectHandler = function () {
		var selectedItem = chart.getSelection()[0];
        var value = dataTable.getValue(selectedItem.row, 0);
	};
	*/
}

function columnChart(arrayList) {
	var dataTable = google.visualization.arrayToDataTable(arrayList);
	var options = {
			title: '게시판별 게시글 수',
			chartArea: {width:"80%"},
			height: 500,
			hAxis: {
				title: '게시판',
				titleTextStyle: {
					color: 'red'
				}
			}
	};
	var objDiv = document.getElementById('chart_div');
	var chart = new google.visualization.ColumnChart(objDiv);
	chart.draw(dataTable, options);
	/*
	var colSelectHandler = function () {
		var selectedItem = chart.getSelection()[0];
        var value = dataTable.getValue(selectedItem.row, 0);
	};
	*/
}

function pieChart(arrayList) {
	var dataTable = google.visualization.arrayToDataTable(arrayList);
	var options = {
			title: '작성자별 게시글 수',
			chartArea: {width:"80%"},
			height: 500
	};
	var objDiv = document.getElementById('pie_chart_div');
	var chart = new google.visualization.PieChart(objDiv);
	chart.draw(dataTable, options);
}
</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/inc/MainHeader.jsp"/>
<!-- <div class="site-blocks-cover inner-page-cover overlay"
	style="background-image: url(images/hero_1.jpg);" data-aos="fade"
	data-stellar-background-ratio="0.5"> -->
	<div class="container">
		<div
			class="row align-items-center justify-content-center text-center">

			<div class="col-md-12" data-aos="fade-up" data-aos-delay="400">

				<div class="row justify-content-center mb-4">
					<div class="col-md-8 text-center">
						<h1>게시판 이름 지분</h1>
						<p class="lead mb-5">누가누가 많이 썼나?</p>
					</div>
				</div>

			</div>
		</div>
	</div>
</div>

<section class="site-section">
<h2 class="text-black site-section-heading text-center" id="lst">Chart</h2>
	<div class="container">
		<div class="row">
			<div style="width: 100%;">
			<!-- bootstrapBoard -->
				<div class="panel-body">
				</div>

				<!-- bootstrapBoard -->
				
				<!--  boardpage -->
				
				<!-- <div id="month_chart"></div><br><br><br>
				
				<div id="chart_div"></div><br><br><br> -->
				
				<div id="pie_chart_div" ></div>
				
				<!--  boardpage -->
			</div>

		</div>
	</div>
</section>

<jsp:include page="/WEB-INF/views/inc/bottom.jsp"></jsp:include>


<script src="js/jquery-migrate-3.0.1.min.js"></script>
<script src="js/jquery-ui.js"></script>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/owl.carousel.min.js"></script>
<script src="js/jquery.stellar.min.js"></script>
<script src="js/jquery.countdown.min.js"></script>
<script src="js/jquery.magnific-popup.min.js"></script>
<script src="js/bootstrap-datepicker.min.js"></script>
<script src="js/aos.js"></script>

<script src="js/main.js"></script>

</body>
</html>