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

<!DOCTYPE html >
<html>
<head>
<meta content="charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

   <c:choose>
      <c:when test="${size == 0}"> 쪽지가 없습니다.<br>
         <input type="button" value="닫기" onclick="window.close()">
      </c:when>
      <c:otherwise>
         <table>
            <tr>
               <th>보낸이</th>
               <th>제목</th>
               <th>보낸시간</th>
               <th>읽음</th>
            </tr>
            <c:forEach items="${noteList }" var="n">

               <tr>
                  <td>${n.nbNickName }</td>
                  <td><a href="YSnoteContent?nbNo=${n.nbNo}">${n.nbTitle }</a></td>
                  <td><fmt:formatDate value="${ n.nbDate}" pattern="yyyy-MM-dd" /></td>
                  <td><c:choose>
                        <c:when test="${n.nbClick == 0 }"> 안읽음 </c:when>
                        <c:otherwise>읽음</c:otherwise>
                     </c:choose></td>
               </tr>
            </c:forEach>
         </table>
         <c:choose>
            <c:when test="${maxPage < 10 }">
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
                           <a href="YSGetNote?toid=${loginUser.nickName }&pageNum=11">다음</a>
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

                     
                     <c:otherwise>

                     </c:otherwise>
                  </c:choose>
               </c:forEach>
            </c:otherwise>
         </c:choose>



      </c:otherwise>
   </c:choose>
</body>
</html>