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
<script>
$(function() {
	
	$.ajax({
		url : 'ListAllComment',
		type : 'post',
		datatype : "text",
		data : ({
			
			bno: $("#bno").val()
	
		}),
		success : function(result, status, xhr) {
			$("#commentList").append(result);

		},
		error : function(xhr, statusText, error) {
			alert('에러 : ' + statusText + ", " + xhr.status);
		}
	});
	
	
	
	
	$("#btnSubmit").on("click", function() {

		if ($("#comment").val() != "") {
			
			$.ajax({
				url : 'AddComment',
				type : 'post',
				datatype : "text",
				data : ({
					
					bno: $("#bno").val(),
					comment: $("#comment").val(),
					loginUser: $("#loginUser").val()
			
				}),
				success : function(result, status, xhr) {
					$("#commentList").empty();
					$("#commentList").append(result);
					$("#comment").val("");

				},
				error : function(xhr, statusText, error) {
					alert('에러 : ' + statusText + ", " + xhr.status);
				}
			});

		} else {
			alert('댓글 입력');
		}

	});
	
	$("#btnModify").on("click", function() {

		if($("#writer").val() != $("#loginUser").val()){
			
		}else{
			
			$.ajax({
				url : 'Comment',
				type : 'post',
				datatype : "text",
				data : ({
					
					bno: $("#bno").val(),
					comment: $("#comment").val(),
					loginUser: $("#loginUser").val()
			
				}),
				success : function(result, status, xhr) {
					$("#commentList").empty();
					$("#commentList").append(result);
					$("#comment").val("");

				},
				error : function(xhr, statusText, error) {
					alert('에러 : ' + statusText + ", " + xhr.status);
				}
			});
			
		}
			
			
			

	});
	
});


</script>
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

<div id="commentList">


</div>

<form method="post" action="AddComment">

<textarea name="comment" id="comment"></textarea>
<input type="button" name="btnSubmit" id="btnSubmit" value="등록" /> 
<input type="button" name="btnModify" id="btnModify" value="수정" 
	onclick="window.location.href='modifyComment?bno=${fbc.bno }'" />
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