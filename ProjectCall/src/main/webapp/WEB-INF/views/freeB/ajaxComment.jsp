<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<c:forEach var="fbc" items="${fbcList}">
		<div>
			<div>${fbc.comment}</div>
			<div>${fbc.email }</div>
			<div>${fbc.writeDate }</div>
		</div>
	</c:forEach>

