<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
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
	
	$("#btnAddFightResultForm").on("click", function() {
		$(location).attr('href',"addFightResultBoardForm?fightNumber="+ $("#fightNumber").val());
	});
});
</script>

<map name="Map_sys" id="Map_sys">
	<area shape="circle" coords="753,100,60" href="#ser_1">
	<area shape="circle" coords="695,240,60" href="#ser_2">
	<area shape="circle" coords="753,380,60" href="#ser_3">
</map>

<input type="hidden" value="${ loginUser.email }" id="loginUser" />
	<div style="border-bottom: 4px solid #7092BE; margin: 30px 0"><span style="font-size: 48px"><b>MY PAGE</b></span> <span>나의 대결 현황과 개인정보를 수정할 수 있습니다.</span></div>
	 <div style="position:absolute">  
        <div style="position:relative;left:230px;top:123px;"><img src="resources/uploadimages/${ member.profilPhoto }" width="240px" height="240px" style="border-radius: 115px;"/></div>  
    </div>  
    <img src="resources/images/mypage_img.png" width="980px" usemap="#Map_sys"/>
	<h3>${ member.nickName }님의 정보</h3>
	<div id="ser_1">
		<h4>내 랭킹</h4>
		${ member.level }위 / ${ winningRate }% 
	</div>
	<div id="ser_2">
		<h4>승패(승률)</h4>
		${ member.win }승 / ${ member.lose }패(${ winningRate }%) 
	</div>
	
	<div id="ser_3">
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
				<th>결과 등록</th>
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
					<c:if test="${f.fbresult == 0 }">
							결과를 등록해 주세요
						</c:if>
						<c:if test="${f.fbresult != 0 }">
							결과 승인 대기중
						</c:if>
					</td>
					<td>
						<c:if test="${f.fbresult == 0 }">
							<input type="button" value="결과등록" id="btnAddFightResultForm"
								name="btnAddFightResultForm" />
							<input type="hidden" id="fightNumber" value="${ f.fbNo }" />
						</c:if>
						<c:if test="${f.fbresult != 0 }">
							결과등록 완료
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<button id="btnUpdateMemberInfo" data-toggle="modal" data-target="#passwordCheck">
		회원정보 수정</button>
	<input type="button" id="btnDropMember" value="회원탈퇴"  />
	<input type="hidden" id="loginUser" value='${ loginUser.email }' />
	
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
								<input type="hidden" name="loginUser" id="loginUser" value="${ loginUser.email }" />
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
