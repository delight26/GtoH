<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.project.call.domain.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<meta content="charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		Member m = (Member) session.getAttribute("loginUser");
	%>

	<!-- email <-세션처리 -->
	<a href="YSGetNote?toid=<%=m.getNickName()%>&pageNum=1"
		onClick="window.open(this.href, '', 'width=400, height=430'); return false;">쪽지보기</a>
	<table>
		<tr>
			<th>사진</th>
			<th>랭킹</th>
			<th>닉네임</th>
			<th>레벨</th>
			<th>전적</th>
			<th>승률</th>
			<th>지역</th>
		</tr>
		<c:forEach items="${rankingList }" var="l">
			<c:choose>
				<c:when test="${l.name =='관리자' }">
				</c:when>
				<c:otherwise>
					<tr>
						<th><img src="http://placehold.it/70x70"></th>
						<th>${l.rank }</th>
						<th><a
							href="YSAddNoteForm?email=${l.email }&nickName=${l.nickName}"
							onClick="window.open(this.href, '', 'width=400, height=430'); return false;">${l.nickName }</a></th>
						<th>${l.level }</th>
						<th>${l.win }승${ l.lose}패</th>

						<th><c:choose>
								<c:when test="${l.win == 0 }">0%
								</c:when>
								<c:otherwise>
									
									${(l.lose+l.win)/l.win * 100 }%</th>
									</c:otherwise>
			</c:choose>
						<th>${l.area }</th>
					</tr>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</table>
</body>
</html>