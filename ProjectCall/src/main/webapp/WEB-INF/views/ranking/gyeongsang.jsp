<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<table>
	<tr>
		<th>경상도 Top 10</th>
	</tr>
	<c:forEach var="gyeongsang" items="${ gyeongsang }">
	<tr>
		<td><c:if test="${ gyeongsang.profilPhoto == null }">
			<img src="resources/images/member/profile_default.png" width="50px" height="50px"/></c:if>
		<c:if test="${ gyeongsang.profilPhoto != null }">
			<img src="resources/images/member/${ gyeongsang.profilPhoto }" width="50px" height="50px"/></c:if>
		</td>
		<td>${ gyeongsang.nickName }</td>
		<td>${ gyeongsang.addr }</td>
	</tr>
	</c:forEach>
</table>