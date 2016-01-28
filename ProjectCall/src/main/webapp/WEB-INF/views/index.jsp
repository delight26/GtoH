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
			<%@ include file="/WEB-INF/views/importPage/header.jsp"%>
		</header>
		<div class="clear"></div>
		<nav>
		<%@ include file="/WEB-INF/views/importPage/nav.jsp"%>
		<%-- <c:if test="${ empty sessionScope.userLoginInfo }">
			
		</c:if> --%>
		<%-- <c:if test="${ not empty sessionScope.userLoginInfo }">
			<%@ include file="/WEB-INF/views/importPage/login_nav.jsp" %>
		</c:if> --%>
		</nav>	
		<c:if test="${ not empty param.body }">
			<jsp:include page="${ param.body }"/>
		</c:if>
		<div class="clear"></div>
			<%@ include file="/WEB-INF/views/importPage/footer.jsp"%>
	</div>
</body>
</html>