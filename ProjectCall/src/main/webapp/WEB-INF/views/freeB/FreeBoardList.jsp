<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table>
	<tr>
		<th>글 번호</th>
		<th>제목</th>
		<th>작성자</th>
		<th>등록일</th>
		<th>조회 수</th>
	</tr>
	
	<c:forEach var="frb" items="${frbList }">
	<tr>
		<td>${frb.frbNo }</td>
		<td><a href="FreeBoardContent?frbNo=${frb.frbNo }">
		${frb.frbTitle }</a></td>
		<td>${frb.frbEmail }</td>
		<td>${frb.frbWriteDate }</td>
		<td>${frb.frbHit}</td>
	</tr>
	</c:forEach>
	
	<input type="button" name="write" value="글쓰기"
		onclick="window.location.href='FreeBoardWrite'">
	<input type="button" name="AllList"	 value="목록보기"
		onclick="window.location.href='FreeBoardList'" />


</table>
</body>
</html>