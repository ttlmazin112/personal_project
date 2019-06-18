<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SH투자전문그룹</title>
<link href="css/default.css" rel="stylesheet" type="text/css">
<link href="css/subpage.css" rel="stylesheet" type="text/css">
<title>구글 차트</title>
<style>
	div {
		float:none; 
	}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://www.gstatic.com/charts/loader.js"></script>
<script>
	// 구글 시각화 API를 로딩하는 메소드
	google.charts.load('current', {packages: ['corechart']});
	
	// 구글 시각화 API가 로딩이 완료되면,
	// 인자로 전달된 콜백함수를 내부적으로 호출하여 차트를 그리는 메소드
	google.charts.setOnLoadCallback(drawChart);
	
	function drawChart() {
		
		columnChart2();
		stackedColumnChart2();
		lineChart2();
		
	}

	// 묶은 세로 막대형 차트 1
	function columnChart1() {
		// 버튼 클릭 시 ajax를 사용하여 서버로부터 json 배열 객체를 가져왔다고 가정함
		var arr = [
			['년도', '매출액', '비용'],
			['2016', 1000, 400],
			['2017', 1170, 460],
			['2018', 660, 1120],
			['2019', 1030, 540]
		];
		// 실 데이터를 가진 데이터테이블 객체를 반환하는 메소드
		var dataTable = google.visualization.arrayToDataTable(arr);
		// 옵션객체 준비
		var options = {
				title: '회사 실적',
				hAxis: {
					title: '2019년도',
					titleTextStyle: {
						color: 'red'
					}
				}
		};
		// 차트를 그릴 영역인 div 객체를 가져옴 
		var objDiv = document.getElementById('column_chart_div1');
		// 인자로 전달한 div 객체의 영역에 컬럼차트를 그릴수 있는 차트객체를 반환
		var chart = new google.visualization.ColumnChart(objDiv);
		// 차트객체에 데이터테이블과 옵션 객체를 인자로 전달하여 차트 그리는 메소드
		chart.draw(dataTable, options);
	} // drawColumnChart1()의 끝

	// 묶은 세로 막대형 차트 2
	function columnChart2() {
		var arr = [
			['날짜', 'SH그룹', '동종업계'],
			['2018년 1분기', 70, 50],
			['2018년 2분기', 80, 40],
			['2018년 3분기', 90, 50],
			['2018년 4분기', 200, 100],
			['2019년 1분기', 220, 110],
			['2019년 2분기', 180, 90],
		];
		
		var dataTable = google.visualization.arrayToDataTable(arr);
		
		var options = {
				title: 'SH그룹과 동종업계 수익률 비교',
				hAxis: { minValue: 0 }  // 최저 라인을 0부터 시작하게 함
		};
		
		var objDiv = document.getElementById('column_chart_div2');
		var chart = new google.visualization.ColumnChart(objDiv);
		chart.draw(dataTable, options);
	} // drawColumnChart2()의 끝
	
	// 누적 세로 막대형 차트 1
	function stackedColumnChart1() {
		var arr = [
			['장르', '판타지 & SF', '로맨스', '미스터리/스릴러', '일반', '서부극', '문학', {role: 'annotation'}],
			['2010', 10, 24, 20, 32, 18, 5, ''],
			['2020', 16, 22, 23, 30, 16, 9, ''],
			['2030', 28, 19, 29, 30, 12, 13, '']
		];
		
		var dataTable = google.visualization.arrayToDataTable(arr);
		
		var options = {
				width: 600,
				height: 400,
				legend: { position: 'top', maxLines: 3 },
				bar: { groupWidth: '75%' },
				isStacked: true
		};
		
		var objDiv = document.getElementById('stacked_column_chart_div1');
		var chart = new google.visualization.ColumnChart(objDiv);
		chart.draw(dataTable, options);
	}
	
	// 누적 세로 막대형 차트 2
	function stackedColumnChart2() {
		var arr = [
			['날짜', 'SH그룹', '동종업계'],
			['2018년 1분기', 70, 50],
			['2018년 2분기', 80, 60],
			['2018년 3분기', 90, 70],
			['2018년 4분기', 200, 80],
			['2019년 1분기', 220, 90],
			['2019년 2분기', 180, 100],
		];
		
		var dataTable = google.visualization.arrayToDataTable(arr);
		
		var options = { isStacked: true };
		
		
		var objDiv = document.getElementById('stacked_column_chart_div2');
		var chart = new google.visualization.ColumnChart(objDiv);
		chart.draw(dataTable, options);
	}
	
	// 꺾은선형 차트 1
	function lineChart1() {
		var arr = [
			['년도', '매출액', '비용'],
			['2016', 1000, 400],
			['2017', 1170, 460],
			['2018', 660, 1120],
			['2019', 1030, 540]
		];
		
		var dataTable = google.visualization.arrayToDataTable(arr);

		var options = {
				title: '회사 실적',
				curveType: 'function',  // 꺾은선을 곡선으로 표시함
				legend: { position: 'bottom' }
		};

		var objDiv = document.getElementById('line_chart_div1');
		var chart = new google.visualization.LineChart(objDiv);
		chart.draw(dataTable, options);
	}
	
	// 꺾은선형 차트 2
	function lineChart2() {
		var arr = [
			['날짜', '데이터 A', '데이터 B'],
			['2018-01-02', 1.14, 1.20],
			['2018-01-03', 1.12, 1.16],
			['2018-01-04', 2.22, 1.14],
			['2018-01-05', 1.10, 1.12],
			['2018-01-06', 0.81, 1.11],
		];
		
		var dataTable = google.visualization.arrayToDataTable(arr);
		
		var options = {
				// curveType: 'function',  // 꺾은선을 직선으로 표시하려면 삭제
				legend: { position: 'bottom' },
				vAxis: { minValue: 0 }
		};
		
		var objDiv = document.getElementById('line_chart_div2');
		var chart = new google.visualization.LineChart(objDiv);
		chart.draw(dataTable, options);
	}
	
	// 분산형 차트(산포도) 1
	function scatterChart1() {
		var arr = [
			['나이', '체중'],
			[8,      12],
			[4,      5.5],
			[11,     14],
			[4,      5],
			[3,      3.5],
			[6.5,    7]
		];
		
		var dataTable = google.visualization.arrayToDataTable(arr);
		
		var options = {
				title: '나이 vs. 체중 비교',
				hAxis: { title: '나이', minValue: 0, maxValue: 15 },
				vAxis: { title: '체중', minValue: 0, maxValue: 15 },
				legend: 'none'
		};
		
		var objDiv = document.getElementById('scatter_chart_div1');
		var chart = new google.visualization.ScatterChart(objDiv);
		chart.draw(dataTable, options);
	}
	
	// 분산형 차트(산포도) 2
	function scatterChart2() {
		var arr = [
			['데이터 C', '데이터 D'],
			[1, 2],
			[7, 6],
			[2, 3],
			[6, 6],
			[2, 3],
			[1, 1],
			[3, 1],
			[3, 2],
			[1, 3],
			[3, 2],
			[4, 2],
			[5, 5],
			[6, 5],
			[7, 9]
		];
		
		var dataTable = google.visualization.arrayToDataTable(arr);
		
		var options = {
				title: '데이터 C와 데이터 D',
				hAxis: { title: '데이터 C', minValue: 0, maxValue: 10 },
				vAxis: { title: '데이터 D', minValue: 0, maxValue: 10 },
				legend: 'none'
		};
		
		var objDiv = document.getElementById('scatter_chart_div2');
		var chart = new google.visualization.ScatterChart(objDiv);
		chart.draw(dataTable, options);
	}
	
	// 원형 차트 1
	function pieChart1() {
		var arr = [
			['점수대', '인원수'],
			['90점 이상', 3],
			['80점대', 7],
			['70점대', 5],
			['60점대', 2],
			['60점 미만', 1]
		];
		
		var dataTable = google.visualization.arrayToDataTable(arr);
		
		var options = {
				title: '점수대별 인원수'
		};
		
		var objDiv = document.getElementById('pie_chart_div1');
		var chart = new google.visualization.PieChart(objDiv);
		chart.draw(dataTable, options);
		
		// select(선택) 이벤트 핸들러(처리)용 함수를 무명함수로 정의
		var selectHandler = function () {
			var selectedItem = chart.getSelection()[0];
	        var value = dataTable.getValue(selectedItem.row, 0);
	        alert('선택한 항목은 ' + value + ' 입니다.');
		};
// 		function selectHandler() {
// 			var selectedItem = chart.getSelection()[0];
// 	        var value = dataTable.getValue(selectedItem.row, 0);
// 	        alert('선택한 항목은 ' + value + ' 입니다.');
// 		}
		
		// 적용할 차트, 적용할 이벤트명, 이벤트 핸들러 함수를 인자로 이벤트 리스너에 등록
		google.visualization.events.addListener(chart, 'select', selectHandler);
	}
	
	$(document).ready(function () {
		$('#btn').click(function () {
			drawChart();
		});
	});
</script>
</head>
<body>
<div id="wrap">
<!-- 헤더가 들어가는 곳 -->
<jsp:include page="/WEB-INF/views/inc/top.jsp"></jsp:include>
<!-- 헤더가 들어가는 곳 -->

<!-- 본문 들어가는 곳 -->
<!-- 서브페이지 메인이미지 -->
<div id="sub_img"></div>
<!-- 서브페이지 메인이미지 -->
<!-- 왼쪽메뉴 -->
<nav id="sub_menu">
 <ul>
<li><a href="investment.do">회사실적</a></li>
<li><a href="investment2.do">업계비교</a></li>

</ul> 

</nav>
	
	<div id="column_chart_div2" style="width: 600px; height: 400px; margin: 0px 300px;"></div>
	<div id="stacked_column_chart_div2" style="width: 900px; height: 500px; margin: 0px 50px"></div>
	<div id="line_chart_div2" style="width: 900px; height: 500px; margin: 0px 50px"></div>


	



	
</body>
</html>


 




    