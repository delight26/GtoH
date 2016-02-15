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
<script type="text/javascript">
function aggrodelete(frbNo){
	conf = confirm("정말 삭제하시겠습니까?");
	if(conf){
		document.location.href="aggrodelete?frbNo=" + frbNo;
	}
}
</script>
<style>
.eachComment {
	background-color: #eaeaea;
	border: 2px solid white;
    border-radius: 10px;
    padding: 0px 0px 0px 0px;
}
#commentImg {
	max-width: 100px;
	width: 100%;
}
@media ( max-width : 799px) {
	#img {
		display: none;
	}
}
</style>
</head>
<body>
	
	
	<div class="content" >
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
			<div class="col-lg-2 col-md-2 col-sm-3 col-xs-3 td th">글쓴이</div>
			<div class="col-lg-4 col-md-4 col-sm-3 col-xs-9 td">${frb.frbWriter }</div>
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
				<c:if test="${frb.photo1 != null }">
					<img src="${pageContext.request.contextPath}/resources/uploadimages/${frb.photo1 }"/><br />
				</c:if>
				${frb.frbContent }
			</div>
		</div>
	</div>
	<div>
		<input type="hidden" id="frbNo" value="${frb.frbNo }" />
		<c:set var="frbEmail" value="${frb.frbEmail }"/>
		<c:set var="nickName" value="admin" />
		<c:if test="${loginUser.email == frbEmail || loginUser.nickName == nickName}">
			 <a href="agrroupdate?frbNo=${frb.frbNo }" data-toggle="modal" data-target="#myModal"><img src="resources/images/btn_modify.gif" width="50px" style="border-radius: 4px;"/></a>
			 <a href="javascript:aggrodelete(${frb.frbNo })"><img src="resources/images/btn_delete.gif" width="50px" style="border-radius: 4px;"/></a>
		</c:if>
		<div style="float: right;">
			<a href="aggropre?frbNo=${frb.frbNo }&pageNum=${pageNum }"><img src="resources/images/btn_pre.jpg" width="70px" style="border-radius: 4px;"/></a>
			<a href="aggronext?frbNo=${frb.frbNo }&pageNum=${pageNum }"><img src="resources/images/btn_next.jpg" width="70px" style="border-radius: 4px;"/></a>
			<a href="agrroboard?pageNum=${pageNum }"><img src="resources/images/btn_list.gif" width="70px" style="border-radius: 4px;"/></a>
		</div>
	</div>
<div id="comment">
</div>
<div>
	<div class="col-lg-11 col-md-11 col-sm-11 col-xs-11">
	 	<textarea class="form-control" rows="2" id="content" name="content"></textarea>
	</div>
	<div class="col-md-1">
		<input type="button" value="댓글  달기" class="btn btn-md btn-info" style="margin-top: 10px" onclick="commentwrite('${ loginUser.email }')"/>
	</div>
</div>
	
</body>
</html>