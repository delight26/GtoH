<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<meta content="charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="">쪽지보기</a>
	<table>
		<tr>
			<th>사진</th>
			<th>랭킹</th>
			<th>닉네임</th>
			<th>전적</th>
			<th>승률</th>
			<th>지역</th>
		</tr>
		<c:forEach items="${lankingList }" var="l">
		
			<tr>
			<th><img src="http://placehold.it/70x70"></th>
			<th>${l.level }</th>
			<th><a href="YSAddNoteForm?email=${l.email }&nikname=${l.nikname}" onClick="window.open(this.href, '', 'width=400, height=430'); return false;">${l.nikname }</a></th>
			<th>${l.win }승 ${ l.lose}패</th>
			<th>${(l.lose+l.win)/l.win * 100 }%</th>
			<th>${l.area }</th>
		</tr>
		</c:forEach>
	</table>
</body>
</html>