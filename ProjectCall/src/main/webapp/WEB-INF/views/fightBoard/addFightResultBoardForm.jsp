<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script>
$(function() {
	
	$("#btnSubmit").on("click", function() {
		
		if($("#title").val() == '') {
			alert("제목을 입력해주세요");
		} else {
			
// 			if($("#photo").val() == null) {
// 				alert("최소한 한 장 이상의 사진을 올려주세요");
// 			} else {
				$("#addFightResultBoardForm").submit();
// 			}
			
		}
		
	});
	
	$("#btnCancel").on("click", function() {
		if(confirm('작성을을 취소하시겠습니까?')) {
			$(location).attr('href',"myPage?loginUser=" + $("#loginUser").val());
		} 
	});

	

});
</script>
</head>
<body>
	<form id="addFightResultBoardForm" action="addFightResultBoardResult"
		enctype="multipart/form-data" method="post">
		
		<table>
			<tr>
				<th>제목</th>
				<td>
					<input type="text" name="title" id="title" />
					<input type="hidden" name="fightNumber" id="fightNumber"
						value="${ fight.fbNo }" />
				</td>
			</tr>
			<tr>
				<th>대결날짜</th>
				<td>
					${ fight.fbResultDate }
				</td>
			</tr>
			<tr>
				<th>참가자</th>
				<td>
					${ fight.fbP1 } vs ${ fight.fbP2 }
				</td>
			</tr>
			<tr>
				<th>사진</th>
				<td><input type="file" name="photo"></td>
			</tr>
			
			<tr>
				<th>승자</th>
				<td>
					<select name="winner">
						<option value="${ fight.fbP1 }">${ fight.fbP1 }</option>
						<option value="${ fight.fbP2 }">${ fight.fbP2 }</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					<textarea name="content" rows="10" cols="10"></textarea>
				</td>
			</tr>
			
		</table>
		<input type="hidden" name="loginUser" id="loginUser" value="${loginUser.email }" />
		<input type="button" id="btnSubmit" value="완료"/>
		<input type="button" id="btnCancel" value="취소"/>
	</form>
</body>
</html>














