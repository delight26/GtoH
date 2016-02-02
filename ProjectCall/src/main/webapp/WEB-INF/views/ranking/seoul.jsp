<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<table>
	<tr>
		<th>서울 Top 10</th>
	</tr>
	<c:forEach var="seoul" items="${ seoul }">
	<tr>
		<td><c:if test="${ seoul.profilPhoto == null }">
			<img src="resources/images/member/profile_default.png" width="50px" height="50px"/></c:if>
		<c:if test="${ seoul.profilPhoto != null }">
			<img src="resources/images/member/${ seoul.profilPhoto }" width="50px" height="50px"/></c:if>
		</td>
		<td>${ seoul.nickName }</td>
		<td>${ seoul.addr }</td>
	</tr>
	</c:forEach>
</table>