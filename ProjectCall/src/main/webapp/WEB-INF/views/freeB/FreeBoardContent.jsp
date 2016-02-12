<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.project.call.domain.*, java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<script src="resources/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/freeboard.js"></script>
<table>
	<tr>
		<th>글 번호 ${frb.frbNo }</th><th>조회 ${frb.frbHit }</th>
	</tr>
	<tr>
		<th>글 제목</th><td> ${frb.frbTitle }</td>
	</tr>
	<tr>
		<th>작성일 ${frb.frbWriteDate }&nbsp;&nbsp;&nbsp;</th><th>작성자 ${frb.frbWriter }</th>		
	</tr>
	
	<tr>
	<th>내용<br/>${frb.frbContent }</th>
	</tr>
	
	
</table>
<div>
<img src="resources/uploadimages/${frb.photo1 }"/>
</div>

<<<<<<< HEAD
<div id="commentList">
</div>

=======
>>>>>>> refs/remotes/origin/class401-Hyunsu
<form method="post" action="AddComment">
<div id="commentList">
</div>
<textarea name="comment" id="comment"></textarea>
<input type="button" name="btnSubmit" id="btnSubmit" value="등록" /> 
<input type="button" name="btnModify" id="btnModify" value="수정" />
<input type="button" name="btnDelete" id="btnDelete" value="삭제" />	
<input type="hidden" name="bno" id="bno" value="${frb.frbNo }"/>
<input type="hidden" name="loginUser" value="${loginUser.email }" id="loginUser" />
<input type="hidden" name="writer" value="${frb.frbWriter }" id="writer" />
</form>


<c:if test="${loginUser.email.equals(frb.frbEmail)}">
	<input type="button" name="Modify" value="수정"
	onclick="window.location.href='modifyForm?frbNo=${frb.frbNo}'" />
	<input type="button" name="Delete" value="삭제"
	onclick="window.location.href='deleteBoard?frbNo=${frb.frbNo}'" />
</c:if>
	
	<input type="button" name="List" value="목록"
	onclick="window.location.href='FreeBoardList'" />
</body>
</html>