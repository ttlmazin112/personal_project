<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>방명록</title>
    
    <style type="text/css">
        #wrap {
            width: 1000px;
            margin: auto auto;
        }
        
        #comment{
            text-align :left;
        }
        
        #listGuestForm{
            text-align :center;
            overflow:scroll; 
            overflow-x:hidden; 
            height:220px;
        }
        
        #writeGuestForm, #pageForm{
            text-align :center;
        }
       
    
    </style>
    
    <script type="text/javascript">
        // 입력값 체크
        function checkValue()
        {
            if(!document.guestbookInfo.guestbook_id.value){
                alert("이름을 입력하세요.");
                return false;
            }
            
            if(!document.guestbookInfo.guestbook_password.value){
                alert("비밀번호를 입력하세요.");
                return false;
            }
            
            if(!document.guestbookInfo.guestbook_content.value){
                alert("내용을 입력하세요.");
                return false;
            }
        }
        
        // 답글창 open
        function openReplyForm(guestbook_no)
        {
            // window.name = "부모창 이름"; 
            window.name = "replyForm";
            // window.open("open할 window", "자식창 이름", "팝업창 옵션");
            window.open("GuestbookReplyForm.do?num="+guestbook_no+"&page=${spage}",
                    "rForm", "width=570, height=350, resizable = no, scrollbars = no");
        }
        
        // 삭제창 open
        function openDelForm(guestbook_no)
        {
            window.name = "parentForm";
            window.open("GuestbookDeleteFormAction.do?num="+guestbook_no+"&page=${spage}",
                    "delForm", "width=570, height=350, resizable = no, scrollbars = no");
        }
        
        // 수정창 open
        function openUpdateForm(guestbook_no)
        {
            window.name = "parentForm";
            window.open("GuestbookUpdateFormAction.do?num="+guestbook_no+"&page=${spage}",
                    "updForm", "width=570, height=350, resizable = no, scrollbars = no");
        }
 
    </script>
        
</head>
<body>
 <jsp:include page="/WEB-INF/views/inc/Header.jsp"/>
    <br>
    
    <hr size="1" width="700">
    <br>
    
<div id="wrap">    
 
    <!-- 글 등록 부분 시작-->
    <div id="writeGuestForm">
        <form name="guestbookInfo" method="post" action="GuestbookWriteAction.do"
            onsubmit="return checkValue()" >
            <table width="700" border="1">
                <tr>
                    <td>이름 : </td>
                    <!-- 로그인했을 경우 방명록의 이름 부분의 아이디를 세팅한다 -->
                    <c:if test="${sessionScope.id!=null}">
                        <td>
                            ${sessionScope.id}
                            <input type="hidden" name="guestbook_id" value="${sessionScope.id}">
                        </td>
                    </c:if>
                    <c:if test="${sessionScope.id==null}">
                         <td><input type="text" name="guestbook_id"></td>
                    </c:if>
                    
                    <td>비밀번호 : </td>
                    <td><input type="password" name="guestbook_password"></td>
                </tr>
                <tr><td colspan="4">&nbsp;</td></tr>
                <tr>
                    <td colspan="4">
                        <textarea rows="7" cols="80" name="guestbook_content"></textarea>
                    </td>
                </tr>
            </table>
            <br>
            <input type="submit" value="등록">
        </form>
    </div>
    <!-- 글 등록 부분 끝-->
 
    <!-- 글 목록 부분 시작 -->
    <div id="listGuestForm">
        <form method="post" name="">
        
            <!-- 방명록 내용 부분 -->
            <div id="comment">
                <c:forEach var="guestbook" items="${requestScope.list}">
                    <hr size="1" width="700">        
                    <c:if test="${guestbook.guestbook_level gt 0}">
                        <c:forEach begin="1" end="${guestbook.guestbook_level * 10}">
                            <img src="images/center/level.gif" style="width: ${wid}px; height: 13px;">
				<img src="images/center/re.gif"> <!-- 답변글일경우 글 제목 앞에 공백을 준다. -->
                        </c:forEach>
                        
                    </c:if>
                    <label>${guestbook.guestbook_id}</label>&nbsp;&nbsp;&nbsp;
                    <label>${guestbook.guestbook_date}</label>&nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="#" onclick="openReplyForm(${guestbook.guestbook_no})">[답변]</a>&nbsp;
                    <a href="#" onclick="openUpdateForm(${guestbook.guestbook_no})">[수정]</a>&nbsp;
                    <a href="#" onclick="openDelForm(${guestbook.guestbook_no})">[삭제]</a><br>
                     ${guestbook.guestbook_content} <br>
                 </c:forEach>    
                     <hr size="1" width="700">
            </div>
                 
            <!-- 페이지 부분 -->
             <div id="pageForm">
                <c:if test="${startPage != 1}">
                    <a href='GuestbookListAction.do?page=${startPage-1}'>[ 이전 ]</a>
                </c:if>
                
                <c:forEach var="pageNum" begin="${startPage}" end="${endPage}">
                    <c:if test="${pageNum == spage}">
                        ${pageNum}&nbsp;
                    </c:if>
                    <c:if test="${pageNum != spage}">
                        <a href='GuestbookListAction.do?page=${pageNum}'>${pageNum}&nbsp;</a>
                    </c:if>
                </c:forEach>
                
                <c:if test="${endPage != maxPage }">
                    <a href='GuestbookListAction.do?page=${endPage+1 }'>[다음]</a>
                </c:if>
            </div> 
        </form>
     </div>
     <!-- 글 목록 부분 끝 -->
 
</div>
     
</body>
</html>


