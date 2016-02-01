<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<table>
	<tr>
		<th>서울 랭킹</th>
	</tr>
	<c:forEach var="seoul" items="${ seoul }">
	<tr>
		<td><img src="resources/images/member/${ seoul.profilPhoto }" width="50px" height="50px"/></td>
		<td>${ seoul.nickName }</td>
		<td>${ seoul.addr }</td>
	</tr>
	</c:forEach>
</table>