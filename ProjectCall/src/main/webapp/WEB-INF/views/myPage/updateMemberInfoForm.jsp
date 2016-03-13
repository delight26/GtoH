<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery-1.11.3.min.js"></script>
<script>
	$(function() {

		if ($("#gender").val() == "남") {
			$("select option[value='남']").attr("selected", true);
		} else if ($("#gender").val() == "여") {
			$("select option[value='여']").attr("selected", true);
		} else {
			$("select option[value='기타']").attr("selected", true);
		}

		$("#password2").blur(function() {
			var pass1 = $("#password").val();
			var pass2 = $("#password2").val();
			$("#passCheck").empty();
			if ($("#password").val() != $("#password2").val()) {
				$("#passCheck").html("  비밀번호가 다릅니다");
				$('#btnSubmit').attr('disabled', true);
			} else {
				$("#passCheck").html("일치합니다");
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
						nickName : $("#nickName").val()
					}),
					success : function(count, status, xhr) {

						if (count != 0) {
							alert('이미 존재하는 별명입니다');
							$('#btnSubmit').attr('disabled', true); //버튼 비활성화 
						} else {
							alert('사용 가능한 별명입니다');
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

			if (confirm('정보수정을 완료하시겠습니까?')) {
				$("#updateMemberInfoForm").submit();
			}

		});

		$("#btnCancel").on(
				"click",
				function() {
					if (confirm('정보수정을 취소하시겠습니까?')) {
						$(location).attr('href',
								"myPage?loginUser=" + $("#loginUser").val());
					}
				});

	});
	
	function readURL(input) {
		if(input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				$('#UploadedImg').attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
</script>
<style type="text/css">
	.phone {
		width: 70px;
	}
</style>
<title>회원정보 변경</title>
</head>
<body>
	<form id="updateMemberInfoForm" action="updateMemberInfoResult"
		enctype="multipart/form-data" method="post">
		<div class="modal-header"
			style="font-size: 25px; background: #E4E3F3; color: #7092BE; border-radius: 8px;">
			<button type="button" class="close" data-dismiss="modal">&times;</button>
			<span><b>개인 정보 수정</b></span>
		</div>
		<div class="modal-body">
			<table>
				<tr>
					<td rowspan="9"><c:if
							test="${ loginUser.profilPhoto != null }">
							<img src="resources/uploadimages/${ loginUser.profilPhoto }" id="UploadedImg"
								width="220px" height="220px" style="border-radius: 110px;" />
						</c:if> <c:if test="${ loginUser.profilPhoto == null }">
							<img src="resources/images/member/mypage_defalut.png" id="UploadedImg"
								width="200px" />
						</c:if></td>
					<td class="td1">Email</td>
					<td>${ member.email }<input type="hidden" name="email"
						id="email" value="${ member.email }" />
					</td>
				</tr>
				<tr>
					<td class="td1">Password</td>
					<td><input type="password" name="password" id="password" placeholder="수정을 원하실때만 입력하세요""/></td>
				</tr>
				<tr>
					<td class="td1">Pass Check</td>
					<td><input type="password" name="password2" id="password2" /><span
						id="passCheck"></span></td>
				</tr>
				<tr>
					<td class="td1">Name</td>
					<td>${ member.name }
					<input type="hidden" name="name" value="${ member.name } " /></td>
				</tr>
				<tr>
					<td class="td1">Nickname</td>
					<td><input type="text" id="nickName" name="nickName"
						value="${ member.nickName }" /> <input type="button" class="btn btn-default btn-xs"
						id="btnNickNameCheck" value="중복확인" /></td>
				</tr>
				<tr>
					<td class="td1">Profile</td>
					<td><input type="file" name="photo" onchange="readURL(this);"></td>
				</tr>
				<tr>
					<td class="td1">Gender</td>
					<td><input type="hidden" id="gender"
						value="${ member.gender }" /> <select name="gender">
							<option value="남">남</option>
							<option value="여">여</option>
							<option value="기타">기타</option>
					</select></td>
				</tr>
				<tr>
					<td class="td1">Phone</td>
					<td><select class="phone" name="phone1">
						<option>010</option>
						<option>011</option>
						<option>016</option>
						<option>019</option>
					</select>-<input type="text" name="phone2" class="phone" min="3" max="4">-<input type="text" name="phone3" class="phone" max="4" min="4">
				</td>
				</tr>
				<tr>
					<td class="td1">Say</td>
					<td><input type="text" name="word" value="${ member.word }" /></td>
				</tr>
			</table>
		</div>
		<div class="modal-footer">
			<input type="hidden" id="loginUser" value="${ loginUser.email }" />
			<input type="button" id="btnCancel"
				class="btn btn-warning btn-block-sm" value="취소" />
				<input type="submit" id="btnSubmit"
				class="btn btn-info btn-block-sm" value="수정" />
		</div>
	</form>









</body>
</html>














