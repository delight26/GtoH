<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/global.css" />
  <script type="text/javascript" src="resources/js/jquery-1.11.3.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

  <script src="${pageContext.request.contextPath}/resources/js/addMember.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/noteCheck.js"></script>

<style>
html {
	padding: 0;
	margin: 0;
}

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
				<%@ include file="/WEB-INF/views/importPage/user_header.jsp"%>
			</c:if>
		</header>
		<div class="clear"></div>
		<nav>
			<%@ include file="/WEB-INF/views/importPage/nav.jsp"%>
		</nav>
		<div class="container-fluid">
			<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1"></div>
			<div class="col-lg-10 col-md-10">
				<c:if test="${ not empty param.body }">
					<jsp:include page="${ param.body }" />
				</c:if>
			</div>
			<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1"></div>
		</div>
		<div class="clear"></div>
		<footer>
			<%@ include file="/WEB-INF/views/importPage/footer.jsp"%>
		</footer>
	</div>
	<div class="container">
		<!-- Modal -->
		<div class="modal fade" id="myModal" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content"></div>
			</div>
		</div>
	</div>
</body>
</html>
