<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/aggrocontent.js"></script>
<link rel="stylesheet" href="resources/css/boardContent.css">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet" href="src/main/webapp/resources/css/boardContent.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="content" >
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
			<div class="col-lg-2 col-md-2 col-sm-3 col-xs-3 td th">글쓴이</div>
			<div class="col-lg-4 col-md-4 col-sm-3 col-xs-9 td">관리자</div>
			<div class="col-lg-2 col-md-2 col-sm-3 col-xs-3 th td">작성일</div>
			<div class="col-lg-4 col-md-4 col-sm-3 col-xs-9 td">
				<fmt:formatDate value="${frb.frbWriteDate }" pattern="yyyy-MM-dd" />
			</div>
		</div>
	
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 td">
			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 th td">제목</div>
			<div class="col-lg-10 col-md-10 col-sm-10 col-xs-9 td">${frb.frbTitle }</div>
		</div>
	
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 td">
			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 th td">내용</div>
			<div class="col-lg-10 col-md-10 col-sm-10 col-xs-9 td">
				${frb.frbContent }
			</div>
		</div>
	</div>
	<div>
		<input type="hidden" id="frbNo" value="${frb.frbNo }" />
		<div style="float: right;">
<%-- 		<a href="aggropre?frbNo=${frb.frbNo }&pageNum=${pageNum }"><img src="resources/images/btn_pre.jpg" width="70px" style="border-radius: 4px;"/></a> 
		<a href="aggronext?frbNo=${frb.frbNo }&pageNum=${pageNum }"><img src="resources/images/btn_next.jpg" width="70px" style="border-radius: 4px;"/></a> --%>
		<a href="javascript:history.back();"><img src="resources/images/btn_list.gif" width="70px" style="border-radius: 4px;"/></a>
		</div>
	</div>
</body>
</html>