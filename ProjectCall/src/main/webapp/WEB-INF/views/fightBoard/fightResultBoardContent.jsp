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
	
	$("#btnConfirmResult").on("click", function() {
		
		if(confirm('승부결과를 승인하시겠습니까?')) {
			$(location).attr('href',"confirmFightResult?no=" + $("#no").val());
		}
		
	});
	
	$("#btnDenyResult").on("click", function() {
		
		if(confirm('승부결과를 반려하시겠습니까?')) {
			 $("#denyResultMessageForm").submit();
		}
		
	});
	
	$("#btnUpdate").on("click", function() {
		
		if(confirm('승부결과를 수정하시겠습니까?')) {
			$(location).attr('href',"updateFightResultBoardForm?no=" + $("#no").val()
					+"&fightNumber=" + $("#fightNumber").val());
		}
		
	});
	
	$("#btndelete").on("click", function() {
		
		if(confirm('승부결과를 삭제하시겠습니까?')) {
			$(location).attr('href',"deleteFightResultBoard?no=" + $("#no").val());
		}
		
	});

});
</script>
<style>
td, th {
	border: 1px solid black;
}
</style>
<title>승부결과</title>
</head>
<body>
	<form id="addFightResultBoardForm" action="addFightResultBoardResult"
		enctype="multipart/form-data" method="post">
		
		<table>
			<tr>
				<th>글번호</th>
				<td>
					${ frb.no }
				</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>
					${ frb.title }
				</td>
			</tr>
			<tr>
				<th>대결날짜</th>
				<td>
					${ fight.fbResultDate }
				</td>
			</tr>
			<tr>
				<th>글쓴이</th>
				<td>
					${ frb.writer }
					
				</td>
			</tr>
			<tr>
				<th>참가자</th>
				<td>
					${ fight.fbP1 } vs ${ fight.fbP2 }
				</td>
			</tr>
			<tr>
				<th>승자</th>
				<td>
					${ frb.winner }
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					${ frb.content }
					<img src="resources/uploadimages/${ frb.photo }" width=200px />
				</td>
			</tr>
			<c:if test="${ loginUser.email == 'admin@ghcall.com' }">
				<tr>
					<td>관리자 승인</td>
					<td>
						<c:if test="${ frb.isAdminCheck == 0 }">
							<a href="#" id="btnConfirmResult">승인</a> / 
							<a href="#"
								data-toggle="modal" data-target="#denyMessageForm">거부</a>
							
						</c:if>
						<c:if test="${ frb.isAdminCheck ==1 }">
							승인완료
						</c:if>
					</td>
				</tr>
			</c:if>
		</table>
		<input type="hidden" id="loginUser" value="${ loginUser.email }" />
		<input type="hidden" id="fightNumber" value="${ fight.fbNo }" />
		<c:if test="${ frb.isAdminCheck == 0 }">
			<input type="button" id="btnUpdate" value="수정"/>
		</c:if>
		<c:if test="${ loginUser.email == 'admin@ghcall.com' }">
			<input type="button" id="btndelete" value="삭제"/>
		</c:if>
		
	</form>
	
	<!-- 모달 -->
	<div class="modal fade" id="denyMessageForm" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h5 class="modal-title" style="text-align: center;">반려 메시지를 입력해주세요</h5>
					<p style="visibility:hidden;">1</p>
					<form id="denyResultMessageForm" class="form-horizontal" role="form"
						action="denyFightResult" method="post">
						<div class="form-group">
							<div class="col-sm-12 col-sm-12">
								<textarea class="form-control" name="message" id="message"
									rows="10" cols="30"></textarea>
								<input type="hidden" value="${ frb.no }" id="no" name="no"/>
								<input type="hidden" value="${ frb.writer }" name="writer" id="writer"/>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-sm-12">
								<button type="button" id="btnDenyResult"
									class="btn btn-info btn-block">완료</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	
	
	
	
	
	
</body>
</html>














