<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*,com.project.call.domain.*"%>
<%
   int maxPage = (int) request.getAttribute("maxPage");
   int pageNum = (int) (request.getAttribute("pageNum"));
   int page1 = (pageNum / 10) * (pageNum / 10 + 1);
if(pageNum ==10){
   page1 =0;
}
%>
<!DOCTYPE html>
<html>
<head>
<meta content="charset=UTF-8">
<title>쪽지함</title>
<style>
* {font-size: 13px; font-family:'맑은 고딕'; margin: 0 auto;}
#noteList {width: 380px;border-collapse: collapse;}
#noteList th {
	background: #BCE55C;  
	border-collapse: collapse;
	height: 25px;
	line-height: 25px;
}
#noteList td {
	height: 30px;
	line-height: 30px;
	text-align: center;
}
#noteList td:first-child {
	text-align: left;
	padding-left: 4px;
}
#noteList a:link {text-decoration: none;color: black;}
#noteList a:hover {color: #7092BE;}
#noteList a:visited {text-decoration: none;color: black;}
</style>
</head>
<body>
<img src="resources/images/note_img.gif" width="405px"/>
	 <div style="margin: 0 10px"><a href="YSGetNote?toid=${ loginUser.nickName }&pageNum=1"><img src="resources/images/give.gif"/></a>
      <a href="YSSendNote?email=${ loginUser.email }&pageNum=1"><img src="resources/images/take.gif"/></a></div>
   <c:choose>
      <c:when test="${size == 0}">받은 쪽지가 없습니다.<br>
      </c:when>
      <c:otherwise>
      <p style="float:right; margin: 5px 15px 5px 0">받은 쪽지 : ${size}</p>
         <table id="noteList">
            <tr>
               <th>제목</th>
               <th>보낸이</th>
               <th>보낸시간</th>
               <th>확인</th>
            </tr>
            <c:forEach items="${noteList}" var="n">
               <tr>
                  <td><a href="YSnoteContent?nbNo=${n.nbNo}">${n.nbTitle }</a></td>
                  <td>${n.nbNickName}</td>
                  <td><fmt:formatDate value="${ n.nbDate}" pattern="yy-MM-dd" /></td>
                  <td><c:choose>
                        <c:when test="${n.nbClick == 0 }"><img src="resources/images/notread.jpg"/></c:when>
                        <c:otherwise><img src="resources/images/read.jpg"/></c:otherwise>
                     </c:choose></td>
               </tr>
            </c:forEach>
         </table>
         <c:choose>
            <c:when test="${maxPage <= 10 }">
               <c:forEach var="i" begin="1" end="${maxPage}">
                  <c:choose>
                     <c:when test="${pageNum == i }">${i }</c:when>
                     <c:otherwise>
                        <a href="YSGetNote?toid=${loginUser.nickName }&pageNum=${i}">${i }</a>
                     </c:otherwise>
                  </c:choose>
               </c:forEach>
            </c:when>
            <c:otherwise>
               <c:forEach var="i" begin="1" end="${maxPage -1}">
                  <c:choose>
                     <c:when test="${pageNum <= 10 && i<= 10 }">
                        <c:choose>
                           <c:when test="${pageNum == i }">${i }</c:when>
                           <c:otherwise>
                              <a href="YSGetNote?toid=${loginUser.nickName }&pageNum=${i}">${i }</a>
                           </c:otherwise>
                        </c:choose>
                        <c:if test="${i==10 }">
                           <a href="YSGetNote?toid=${loginUser.nickName }&pageNum=11"><img src="resources/images/note_next.jpg"/></a>
                        </c:if>
                     </c:when>
                     <c:when test="${pageNum > 10 && i > 10}">
                        <c:if test="${i==11 }">
                           <a href="YSGetNote?toid=${loginUser.nickName }&pageNum=10">이전</a>
                        </c:if>
                        <c:choose>
                           <c:when test="${pageNum == i }">${i }</c:when>
                           <c:otherwise>
                              <a href="YSGetNote?toid=${loginUser.nickName }&pageNum=${i}">${i }</a>
                           </c:otherwise>
                        </c:choose>
                        <c:if test="${i==20 }">
                           <a href="YSGetNote?toid=${loginUser.nickName }&pageNum=11">다음</a>
                        </c:if>
                     </c:when>
                  </c:choose>
               </c:forEach>
            </c:otherwise>
         </c:choose>
      </c:otherwise>
   </c:choose>
   <br/><img src="resources/images/note_close.jpg" onclick="window.close()" width="50px" style="padding-left: 170px"/>
</body>
</html>