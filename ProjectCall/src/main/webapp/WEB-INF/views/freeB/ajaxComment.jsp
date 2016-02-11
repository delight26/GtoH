<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<c:forEach var="fbc" items="${fbcList}">
		
		<div>
		
			<div id="replayComment">${fbc.comment}</div>
			
			
			<c:if test="${loginUser.email == fbc.email }">
			<a href="replayModify">수정</a> 
			<a href="#">삭제</a>
			</c:if>
			
			
			<div>${fbc.email }</div>
			<div>${fbc.writeDate }</div>
		
		
		</div>
	</c:forEach>

