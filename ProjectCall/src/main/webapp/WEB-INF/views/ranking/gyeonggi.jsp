<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<table class="rank">
	<tr>
		<th colspan="5">경기도 Top 10</th>
	</tr>
	<c:forEach var="gyeonggi" items="${ gyeonggi }" varStatus="i">
	<tr>
		<td>
		<c:if test="${ i.count == 1 }"><img src="resources/images/gold.jpg" width="40px" height="50px"/></c:if>
		<c:if test="${ i.count == 2 }"><img src="resources/images/silver.jpg" width="40px" height="50px"/></c:if>
		<c:if test="${ i.count == 3 }"><img src="resources/images/bronze.jpg" width="40px" height="50px"/></c:if>
		<c:if test="${ i.count >= 4 }">${ i.count }</c:if>
		</td>
		<td><c:if test="${ gyeonggi.profilPhoto == null }">
			<img src="resources/images/member/profile_default.png" width="50px" height="50px"/></c:if>
		<c:if test="${ gyeonggi.profilPhoto != null }">
			<img src="resources/images/member/${ gyeonggi.profilPhoto }" width="50px" height="50px"/></c:if>
		</td>
		<td>${ gyeonggi.nickName }</td>
		<td>${ gyeonggi.addr }</td>
		<td>${ gyeonggi.point }point</td>
	</tr>
	</c:forEach>
</table>