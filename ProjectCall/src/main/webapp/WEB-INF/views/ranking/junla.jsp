<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<table>
	<tr>
		<th>전라도 Top 10</th>
	</tr>
	<c:forEach var="junla" items="${ junla }">
	<tr>
		<td><c:if test="${ junla.profilPhoto == null }">
			<img src="resources/images/member/profile_default.png" width="50px" height="50px"/></c:if>
		<c:if test="${ junla.profilPhoto != null }">
			<img src="resources/images/member/${ junla.profilPhoto }" width="50px" height="50px"/></c:if>
		</td>
		<td>${ junla.nickName }</td>
		<td>${ junla.addr }</td>
	</tr>
	</c:forEach>
</table>