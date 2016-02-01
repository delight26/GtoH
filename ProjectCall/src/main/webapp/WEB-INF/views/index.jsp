<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="resources/css/global.css" />
<title>Insert title here</title>
<style>
	.clear {
		clear: both;
	}
</style>
</head>
<body>
	<div id="main">
		<header>
		<c:if test="${ empty sessionScope.loginUser }">
			<%@ include file="/WEB-INF/views/importPage/visitor_header.jsp"%>
		</c:if>
			<c:if test="${ not empty sessionScope.loginUser }">
			<%@ include file="/WEB-INF/views/importPage/user_header.jsp" %>
		</c:if>
		</header>
		<div class="clear"></div>
		<nav>
		<%@ include file="/WEB-INF/views/importPage/nav.jsp"%>
		</nav>	
		<c:if test="${ not empty param.body }">
			<jsp:include page="${ param.body }"/>
		</c:if>
		<div class="clear"></div>
		<footer>
			<%@ include file="/WEB-INF/views/importPage/footer.jsp"%>
		</footer>
	</div>
</body>
</html>
