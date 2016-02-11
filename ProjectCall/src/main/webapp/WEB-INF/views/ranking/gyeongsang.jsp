<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<link rel="stylesheet" type="text/css" href="resources/css/rankTable.css" />
<table class="rank">
	<tr>
		<td colspan="4" style="font-size: 40px; border: none; padding-bottom:15px">
		<img src="resources/images/top10_3.jpg" width="200px"/><b>경상</b></td>
	</tr>
	<tr>
		<th>RANK</th>
		<th>NICKNAME</th>
		<th>AREA</th>
		<th>POINT</th>
	</tr>
	<c:forEach var="gyeongsang" items="${ gyeongsang }" varStatus="i">
	<tr>
		<td style="font-size: 25px;">
		<c:if test="${ i.count == 1 }"><img src="${pageContext.request.contextPath}/resources/images/gold.jpg" width="30px" height="40px"/></c:if>
		<c:if test="${ i.count == 2 }"><img src="${pageContext.request.contextPath}/resources/images/silver.jpg" width="30px" height="40px"/></c:if>
		<c:if test="${ i.count == 3 }"><img src="${pageContext.request.contextPath}/resources/images/bronze.jpg" width="30px" height="40px"/></c:if>
		<c:if test="${ i.count >= 4 }">${ i.count }</c:if>
		</td>
		<td id="nameTd"><c:if test="${ gyeongsang.profilPhoto == null }">
			<img src="resources/images/member/profile_default.png" width="40px" height="40px"/></c:if>
		<c:if test="${ gyeongsang.profilPhoto != null }">
			<img src="resources/images/member/${ gyeongsang.profilPhoto }" width="30px" height="40px"/></c:if>
		&nbsp;&nbsp;&nbsp;&nbsp;${ gyeongsang.nickName }</td>
		<td>${ gyeongsang.addr }</td>
		<td style="width: 180px;">${ gyeongsang.point }point</td>
	</tr>
	</c:forEach>
</table>