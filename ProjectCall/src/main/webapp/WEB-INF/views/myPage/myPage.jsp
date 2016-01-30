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
<title>마이페이지</title>

<script>
$(function() {
	
	$("#btnpasswordCheck").on("click", function() {

		if ($("#password").val() != "") {
			
			$.ajax({
				url : 'passwordCheck',
				type : 'post',
				datatype : "number",
				data : ({
					
					loginUser: $("#loginUser").val(),
					password : $("#password").val()
					
				}),
				success : function(result, status, xhr) {
					if (result == 1) {
						$("#passwordCheckForm").submit();
					} else {
						alert('비밀번호를 확인해주세요');
					}

				},
				error : function(xhr, statusText, error) {
					alert('에러 : ' + statusText + ", " + xhr.status);
				}
			});

		} else {
			alert('비밀번호를 입력해주세요.');
		}

	});
	
	
	$("#btnDropMember").on("click", function() {
		
		if(confirm('탈퇴하시겠습니까?')) {
			$(location).attr('href',"deleteMember?loginUser=" + $("#loginUser").val());
		}
		
	});

	

});
</script>
</head>
<body>
	<h2>마이페이지</h2>
	
	<h3>${ member.nickName }님의 정보</h3>
	
	<div>
		<h4>내 랭킹</h4>
		${ member.level }위 / ${ winningRate }% 
	</div>
	<div>
		<h4>승패(승률)</h4>
		${ member.win }승 / ${ member.lose }패(${ winningRate }%) 
	</div>
	
	<div>
		<h4>내 포인트 정보</h4>
		총 ${ member.point }포인트 획득 후 ${ member.usepoint }포인트 사용 
	</div>
	
	<div>
		<h4>대결 전적</h4>
		
		<table>
		
			<tr>
				<th>대결 신청일</th>
				<th>대결 시행일</th>
				<th>대결 요청자</th>
				<th>대결 수락자</th>
				<th>대결 결과</th>
			</tr>
			
			<c:forEach var="f" items="${ fightList }">
				<tr>
					<td>
						${ f.fbCallDate }
					</td>
					<td>
						${ f.fbResultDate }
					</td>
					<td>
						${ f.fbP1 }
					</td>
					<td>
						${ f.fbP2 }
					</td>
					<td>
						${ f.fbresult }
					</td>
				</tr>
			</c:forEach>
			
		</table>
		
		
	</div>
	<button id="btnUpdateMemberInfo" data-toggle="modal" data-target="#passwordCheck">
		회원정보 수정</button>
	<input type="button" id="btnDropMember" value="회원탈퇴"  />
	<input type="hidden" id="loginUser" value='${ sessionScope.loginUser }' />
		
	
	<!-- 모달 -->
	
	<div class="modal fade" id="passwordCheck" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h5 class="modal-title" style="text-align: center;">비밀번호를 입력해주세요</h5>
					<p style="visibility:hidden;">1</p>
					<form id="passwordCheckForm" class="form-horizontal" role="form"
						action="updateMemberInfoForm" method="post">
						<div class="form-group">
							<div class="col-sm-12 col-sm-12">
								<input class="form-control" id="password" type="password"
									value="" name="password" placeholder="비밀번호">
								<input type="hidden" name="loginUser" id="loginUser" value="${ sessionScope.loginUser }" />
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-sm-12">
								<button type="button" id="btnpasswordCheck"
									class="btn btn-info btn-block">확인</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	
</body>
</html>














