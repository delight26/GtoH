<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.project.call.domain.*, java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script>
$(function() {
	
	$("#Modify").on("click", function() {
		if($("loginUesr")){
			alert("로그인");				
		} else{
			$(location).attr('href',"Modify");
		}
		
	});
	
	$("#btnSubmit").on("click", function() {
		if($("comment").val() == ""){
			
			alert("댓글 입력");
		}else{
			alert($("#bno").val());
			$.ajax({
				url : 'AddComment',
				type : 'post',
				datatype : "text",
				data : ({
					
					comment: $("#comment").val(),
					loginUser: $("#loginUser").val(),
					bno : $("#bno").val()
					
				}),
				success : function(result, status, xhr) {
					$("#comment").val("");
					$("#commentList").append(result);
					

				},
				error : function(xhr, statusText, error) {
					alert('에러 : ' + statusText + ", " + xhr.status);
				}
			});
		
		}
		
	});
	
	
	
	
	
	
	
	
	
	
	
});



</script>
<title>Insert title here</title>
</head>
<body>
<table>
	<tr>
		<th>글 번호 ${frb.frbNo }</th><th>조회 ${frb.frbHit }</th>
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
<c:if test="${loginUser.email.equals(frb.frbEmail)}">
	<input type="button" name="Modify" value="수정"
	onclick="window.location.href='modifyForm?frbNo=${frb.frbNo}'" />
	<input type="button" name="Delete" value="삭제"
	onclick="window.location.href='deleteBoard?frbNo=${frb.frbNo}'" />
	</c:if>
	
	<input type="button" name="List" value="목록"
	onclick="window.location.href='FreeBoardList'" />
	
	
	<div id="commentList">
	</div>
	
	
	
	
	<form action="AddComment" id="freebcomment" mehod="post">
	
	
	<textarea name="comment" id="comment"></textarea>
	<input type="button" name="btnSubmit" id="btnSubmit" value="등록" />
	
	<input type="hidden" name="bno" id="bno" value="${frb.frbNo }" /> 
	<input type="hidden" name="loginUser" id="loginUser" value="${loginUser.email}" />
	
	
	
	
	</form>
	
	
	
</body>
</html>













