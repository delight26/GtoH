<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<table>
	<tr>
		<th>경기도 Top 10</th>
	</tr>
	<c:forEach var="gyeonggi" items="${ gyeonggi }">
	<tr>
		<td><c:if test="${ gyeonggi.profilPhoto == null }">
			<img src="resources/images/member/profile_default.png" width="50px" height="50px"/></c:if>
		<c:if test="${ gyeonggi.profilPhoto != null }">
			<img src="resources/images/member/${ gyeonggi.profilPhoto }" width="50px" height="50px"/></c:if>
		</td>
		<td>${ gyeonggi.nickName }</td>
		<td>${ gyeonggi.addr }</td>
	</tr>
	</c:forEach>
</table>