<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>        
<html>
<head>
	<title>����� ����</title>

	<style type="text/css">
		#wrap {
			width: 550px;
			text-align: center;
			margin: 0 auto 0 auto;
		}
		
		#deleteReplyForm{
			text-align: center;
		}
	</style>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	
	<script type="text/javascript">
	
		var httpRequest = null;
		
		// httpRequest ��ü ����
		function getXMLHttpRequest(){
			var httpRequest = null;
		
			if(window.ActiveXObject){
				try{
					httpRequest = new ActiveXObject("Msxml2.XMLHTTP");	
				} catch(e) {
					try{
						httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
					} catch (e2) { httpRequest = null; }
				}
			}
			else if(window.XMLHttpRequest){
				httpRequest = new window.XMLHttpRequest();
			}
			
			return httpRequest;	
		}
		
		// ��й�ȣ ���Է½� ���â
		function checkValue(){
			
			var str = $('#frm').serialize();
			console.log('str: ' + str);
			
			$.ajax({
				url: 'GuestbookDeleteActionProcess.do',
				//url : 'GuestbookDeleteForm.do',
				data: str,
				success: function (result) {
					console.log('result: ' + result);
					if(result == 0){
						alert("��й�ȣ�� Ʋ���ϴ�.");
					} 
					else{ // ��й�ȣ ��ġ�� ������ â�� �ݴ´�.
						if (opener != null) {
							// �����(�θ�â) ���ΰ�ħ
							window.opener.document.location.reload(); 
							opener.delForm = null;
					        self.close();
						}
					}
				}
			});
			
			
			
			
			/*
			var form = document.forms[0];
			var num = form.guestbook_no.value;
			var pw = form.guestbook_password.value;
			
			if (!form.guestbook_password.value) {
				alert("��й�ȣ�� �Է����� �ʾҽ��ϴ�.");
				return false;
			}
			else // ��й�ȣ �Է½� Ajax�� �̿��� �����õ�
			{
				var param="num="+num+"&pw="+pw;
				
				httpRequest = getXMLHttpRequest();
				httpRequest.onreadystatechange = checkFunc;
				httpRequest.open("POST", "GuestbookDeleteActionProcess.do", true);	
				httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded'); 
				httpRequest.send(param);
			}
			
			*/
		} // checkValue()
		
 		function checkFunc(){
			if(httpRequest.readyState == 4){
				// ������� �����´�.
				var resultText = httpRequest.responseText;
				console.log('resultText: ' + resultText);
				if(resultText == 0){
					alert("��й�ȣ�� Ʋ���ϴ�.");
				} 
				else{ // ��й�ȣ ��ġ�� ������ â�� �ݴ´�.
					if (opener != null) {
						// �����(�θ�â) ���ΰ�ħ
						window.opener.document.location.reload(); 
						opener.delForm = null;
				        self.close();
					}
				}
			}
		} 
	</script>
	
</head>
<body>

<div id="wrap">
	<br>
	<b><font size="5" color="gray">����</font></b>
	<hr size="1" width="550">
	<br>

	<div id="deleteReplyForm">
		<form name="delGuestbook" target="parentForm" id="frm">
			<input type="hidden" name="guestbook_no" value="${param.num}"/>
			<input type="hidden" name="pageNum" value="<%=request.getParameter("page")%>">
			��й�ȣ :
			<input type="password" name="guestbook_password" maxlength="50">	
			<br><br><br>
			<input type="button" value="����" onclick="checkValue()">
			<input type="button" value="â�ݱ�" onclick="window.close()">
		</form>
	</div>
</div>	

</body>
</html>