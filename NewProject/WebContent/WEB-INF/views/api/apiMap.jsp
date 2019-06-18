<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>지도에 교통정보 표시하기</title>

</head>
<body>
<jsp:include page="/WEB-INF/views/inc/MainHeader.jsp"/>
<h2>찾아오시는 길</h2>
	<div id="map" style="width: 50%; height: 350px;"></div>

	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=6385bc252b63b377c5d48af45ee25509"></script>
	<script>
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = { 
        center: new daum.maps.LatLng(35.157012, 129.055573), // 지도의 중심좌표
        level: 2 // 지도의 확대 레벨
    };

var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

// 지도에 교통정보를 표시하도록 지도타입을 추가합니다
map.addOverlayMapTypeId(daum.maps.MapTypeId.TRAFFIC);    


// 아래 코드는 위에서 추가한 교통정보 지도타입을 제거합니다
// map.removeOverlayMapTypeId(daum.maps.MapTypeId.TRAFFIC);    
</script>

</body>
<h4>버스안내</h4>
10,5-1,20,29-1,31,33,43,52,57,62,77,83,86,87,99,103,110-1,129
<h4>자가용 안내</h4>
서면교차로에서 시청방면 진입-부전시장(일방통행)으로 우호전- 부전역앞에서 우회전
<h4>지하철 안내</h4>
서면역 7번,9번 출구
</html>