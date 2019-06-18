<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<style type="text/css">
form{
overflow: scroll; width: 500px; height: 500px;
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="js/jquery-3.3.1.min.js"></script>
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script src="js/main.js"></script>

</head>
<body>
	<jsp:include page="/WEB-INF/views/inc/MainHeader.jsp"/>

<form action="AdminEmailAction.do" name="manMainForm" method="post" >
<input type="button"  value="탈퇴시키기" onclick="btn(2)">
 <input type="button"  onclick="btn(1)" value="이메일쓰기">
<table border="3">
	<thead>
        <tr>
            <td>ID</td><td>PASSWORD</td><td>NAME</td>
            <td>EMAIL</td>
            <td>삭제</td><td>수정</td><td>탈퇴/이메일<input type="checkbox" id="chAll" style="width: 50px; margin-left: 30px; "></td>
            
        </tr>
 	</thead>
 	<tbody>
        <c:forEach items="${memberlist}" var="member">
        <tr>
            
            <td>${member.id}</td>
            <td>${member.password}</td>
            <td>${member.name}</td>
            <td id="email">${member.email}</td>
      
            <td><a href="MemberDeleteAction.do?id=${member.id}">삭제</a></td>
            <td><a href="memberupdate.do?id=${member.id}">수정</a></td>
             <td><input type="checkbox" name="chBoxId" value="${member.id}" style="width: 50px; margin-left: 30px;" ></td>
<%--              <td><input id="checkbox" type="checkbox" name="chBoxEmail" value="${member.email}" ></td> --%>
        </tr>
        
        </c:forEach>
     </tbody>
    </table>
      
      
 </form> 
    
 <script>
$(document).ready(function() {
	$('#chAll').click(function() {
		if ($('#chAll').is(':checked')){
			$('input:checkbox[name=chBoxId]').prop('checked', true);		
		} else {
			$('input:checkbox[name=chBoxId]').prop('checked', false);
		}
	});
	
	$('#emailAll').click(function() {
		if ($('#emailAll').is(':checked')){
			$('input:checkbox[name=chBoxEmail]').prop('checked', true);
		} else {
			$('input:checkbox[name=chBoxEmail]').prop('checked', false);
		}
		
	})
});

function btn(index) {
    if(index==2){
       var delCheck = window.confirm("해당 회원의 모든 개시글이 삭제 됩니다.\n정말 회원을 삭제 하시겠습니까?");
       
       if(delCheck == true){
          var form = document.forms['manMainForm'];
          form.action='MemberDelActionProcess.do';
          form.submit();
       }
    }else{
    	if($(":checkbox[name='chBoxId']:checked").length==0){
    		alert("하나 이상 선택해 주세요");
    		return;
    	}else{
    	var rowData = new Array();
		var tdArr = new Array();
		var checkbox =[];
		$("input[name=chBoxId]:checked").each(function(i) {
			checkbox.push($(this).val());
		}); 
    		
    		var form = document.forms['manMainForm'];
    	       form.action='AdminEmailAction.do';
    	       form.submit();
    	}
    	}
       
       
    }
 

</script>  
  
</body>
</html>