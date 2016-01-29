<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<meta content="charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<th>보낸이</th>
			<th>제목</th>
			<th>보낸시간</th>
			<th>읽음</th>
		</tr>
		<c:forEach items="${noteList }" var="n">
			<td>${n.nbToid }</td>
			<td><a href="noteContent?noteNumber=${n.nbNo}">${n.nbTitle }</a></td>
			<td>${ n.nbDate}</td>
			<td>
			<c:choose>
<c:when test="${n.nbClick == 0 }"> 안읽음 </c:when>
<c:otherwise>읽음</c:otherwise>
</c:choose> 
</td>
		</c:forEach>
	</table>
</body>
</html>