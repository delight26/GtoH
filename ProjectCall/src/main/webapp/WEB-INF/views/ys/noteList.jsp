<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*,com.project.call.domain.*" %>
<% List<NoticeBoard> pList = (List<NoticeBoard>)request.getAttribute("noteList");
int pageNum = (int)(request.getAttribute("pageNum"));
int page1 = (pageNum/10*10)*(pageNum/10+1);
System.out.print(page1);
%>
<!DOCTYPE html >
<html>
<head>
<meta content="charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%Member m = (Member)session.getAttribute("loginUser"); %>
	<c:choose>
		<c:when test="${noteList.size() == 0 }"> 쪽지가 없습니다.<br>
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
						<td>${n.nbToid }</td>
						<td><a href="YSnoteContent?nbNo=${n.nbNo}">${n.nbTitle }</a></td>
						<td><fmt:formatDate value="${ n.nbDate}" pattern="YYYY-MM-DD" /></td>
						<td><c:choose>
								<c:when test="${n.nbClick == 0 }"> 안읽음 </c:when>
								<c:otherwise>읽음</c:otherwise>
							</c:choose></td>
					</tr>
				</c:forEach>
			</table><%if(pageNum <= 10){ %>
			<div><%for(int i =1; i < 11; i++){%>
		<a href="YSGetNote?toid=<%= m.getNickName() %>&pageNum=<%= i %>">
		<%= i %></a>  <%
			} %><a href="YSGetNote?toid=<%= m.getNickName() %>&pageNum=<%= 11 %>">다음</a>
			</div><%} %>
		</c:otherwise>
	</c:choose>
</body>
</html>