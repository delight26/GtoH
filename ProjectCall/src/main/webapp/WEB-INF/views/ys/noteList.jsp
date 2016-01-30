<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*,com.project.call.domain.*" %>
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
			</table>
			<div><a href="YSGetNote?toid=<%= m.getNickName() %>&pageNum=${noteList.get(0).getNbMaxPage() }">${noteList.get(0).getNbMaxPage() }</a></div>
		</c:otherwise>
	</c:choose>
</body>
</html>