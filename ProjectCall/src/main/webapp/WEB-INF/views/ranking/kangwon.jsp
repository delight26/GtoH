<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<table>
	<tr>
		<th>강원도 Top 10</th>
	</tr>
	<c:forEach var="kangwon" items="${ kangwon }">
	<tr>
		<td><c:if test="${ kangwon.profilPhoto == null }">
			<img src="resources/images/member/profile_default.png" width="50px" height="50px"/></c:if>
		<c:if test="${ kangwon.profilPhoto != null }">
			<img src="resources/images/member/${ kangwon.profilPhoto }" width="50px" height="50px"/></c:if>
		</td>
		<td>${ kangwon.nickName }</td>
		<td>${ kangwon.addr }</td>
	</tr>
	</c:forEach>
</table>