<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<table>
	<tr>
		<th>충청도 Top 10</th>
	</tr>
	<c:forEach var="chungcheong" items="${ chungcheong }">
	<tr>
		<td><c:if test="${ chungcheong.profilPhoto == null }">
			<img src="resources/images/member/profile_default.png" width="50px" height="50px"/></c:if>
		<c:if test="${ chungcheong.profilPhoto != null }">
			<img src="resources/images/member/${ chungcheong.profilPhoto }" width="50px" height="50px"/></c:if>
		</td>
		<td>${ chungcheong.nickName }</td>
		<td>${ chungcheong.addr }</td>
	</tr>
	</c:forEach>
</table>