<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form>
<a href="aggrowrite">글쓰기</a>
	<table>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>글쓴이</th>
			<th>작성일</th>
			<th>조회 수</th>
			<th>댓글 수</th>
		</tr>
		<c:forEach var="agrro" items="${aggroList }" >
		<tr>
		<td>${agrro.frbNo }</td>
		<td><a href="aggrocontent?frbNo=${aggro.frbNo }">${agrro.frbTitle }</a></td>
		<td>${agrro.frbWriter }</td>
		<td>${agrro.frbWriteDate }</td>
		<td>${agrro.frbHit }</td>
		<td>${agrro.frbComment }</td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="6" class="listPage">
				<c:if test="${ startPage > PAGE_GROUP }">
					<a href="productlist?pageNum=${ startPage - PAGE_GROUP }">[이전]</a>
				</c:if>			
				<c:forEach var="i" begin="${ startPage }" end="${ endPage }">
				<c:if test="${ i == currentPage }">
					[${ i }]
				</c:if>			
				<c:if test="${ i != currentPage }">
					<a href="productlist?pageNum=${ i }">[${ i }]</a>
				</c:if>			
				</c:forEach>
				<c:if test="${ endPage < pageCount }">
					<a href="productlist?pageNum=${ startPage + PAGE_GROUP }">[다음]</a>
				</c:if>		
			</td>	
		</tr>
	</table>
</form>
</body>
</html>