<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<table class="rank">
	<tr>
		<th colspan="5">충청도 Top 10</th>
	</tr>
	<c:forEach var="chungcheong" items="${ chungcheong }" varStatus="i">
	<tr>
		<td>
		<c:if test="${ i.count == 1 }"><img src="${pageContext.request.contextPath}/resources/images/gold.jpg" width="40px" height="50px"/></c:if>
		<c:if test="${ i.count == 2 }"><img src="${pageContext.request.contextPath}/resources/images/silver.jpg" width="40px" height="50px"/></c:if>
		<c:if test="${ i.count == 3 }"><img src="${pageContext.request.contextPath}/resources/images/bronze.jpg" width="40px" height="50px"/></c:if>
		<c:if test="${ i.count >= 4 }">${ i.count }</c:if>
		</td>
		<td><c:if test="${ chungcheong.profilPhoto == null }">
			<img src="${pageContext.request.contextPath}/resources/images/member/profile_default.png" width="50px" height="50px"/></c:if>
		<c:if test="${ chungcheong.profilPhoto != null }">
			<img src="${pageContext.request.contextPath}/resources/images/member/${ chungcheong.profilPhoto }" width="50px" height="50px"/></c:if>
		</td>
		<td>${ chungcheong.nickName }</td>
		<td>${ chungcheong.addr }</td>
		<td>${ chungcheong.point }point</td>
	</tr>
	</c:forEach>
</table>