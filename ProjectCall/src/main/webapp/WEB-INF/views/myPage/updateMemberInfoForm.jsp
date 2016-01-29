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
	
	$("#password2").on("blur", function() {
		
		if ($("#password1").val() != $("#password2").val()) {
			alert("비밀번호를 다시 입력해주세요.");
			$('#btnSubmit').attr('disabled', true);
		} else {

			$('#btnSubmit').attr('disabled', false); 
		}
		
	});
	
	$("#btnNickNameCheck").on("click", function() {
		
		if ($("#nickName").val() != "") {

			$.ajax({
				url : 'checkNickName',
				type : 'post',
				datatype : "number",
				data : ({
					loginUser: $("#loginUser").val(),
					nickName : $("#nickName").val()
				}),
				success : function(count, status, xhr) {
				
					if (count != 0) {
						alert('이미 존재하는 별명입니다');
						$('#btnSubmit').attr('disabled', true); //버튼 비활성화 
					} else {
						$('#btnSubmit').attr('disabled', false); //버튼 활성화 
					}

				},
				error : function(request, status, error) {
					alert('에러' + error.code);
				}
			});

		} else {
			alert('별명을 입력해주세요');
			$('#btnSubmit').attr('disabled', true); //버튼 비활성화 
		}
		
	});
	
	$("#btnSubmit").on("click", function() {
		
		if(confirm('정보수정을 완료하시겠습니까?')) {
			
			$("#updateMemberInfoForm").submit();
			
		} 
		
		
		
	});
	
	$("#btnCancel").on("click", function() {
		if(confirm('정보수정을 취소하시겠습니까?')) {
			$(location).attr('href',"myPage?loginUser=" + $("#loginUser").val());
		} 
	});

	

});
</script>
<title>회원정보 변경</title>
</head>
<body>
	<form id="updateMemberInfoForm" action="updateMemberInfoResult">
		
		<table>
			<tr>
				<th>이메일</th>
				<td>${ member.email }</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="password1" id="password1" /></td>
			</tr>
			<tr>
				<th>비밀번호 확인</th>
				<td><input type="password" name="password2" id="password2" /></td>
			</tr>
			<tr>
				<th>이름</th>
				<td>${ member.name }</td>
			</tr>
			<tr>
				<th>별명</th>
				<td>
					<input type="text" id="nickName" name="nickName" value="${ member.nickName }" />
					<button id="btnNickNameCheck">중복확인</button>
				</td>
			</tr>
			<tr>
				<th>성별</th>
				<td>
					<select>
						<option>남</option>
						<option>여</option>
						<option>기타</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td><input type="text" name="phone" value="${ member.phone }" /></td>
			</tr>
			<tr>
				<th>오늘의 한 마디</th>
				<td><input type="text" name="word" value="${ member.word }" /></td>
			</tr>
		</table>
		<input type="hidden" id="loginUser" value="${ sessionScope.loginUser }" />
		
		<input type="button" id="btnSubmit" value="완료"/>
		<input type="button" id="btnCancel" value="취소"/>
	</form>
	
	
	
	
	
	
	
	
	
</body>
</html>














