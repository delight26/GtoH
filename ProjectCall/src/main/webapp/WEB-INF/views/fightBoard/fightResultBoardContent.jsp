<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@  taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet" href="resouces/css/boardContent.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script>
	$(function() {

		$("#btnConfirmResult").on(
				"click",
				function() {

					if (confirm('승부결과를 승인하시겠습니까?')) {
						$(location).attr('href',
								"confirmFightResult?no=" + $("#no").val());
					}

				});

		$("#btnDenyResult").on("click", function() {

			if (confirm('승부결과를 반려하시겠습니까?')) {
				$("#denyResultMessageForm").submit();
			}

		});

		$("#btnUpdate").on(
				"click",
				function() {

					if (confirm('승부결과를 수정하시겠습니까?')) {
						$(location).attr(
								'href',
								"updateFightResultBoardForm?no="
										+ $("#no").val() + "&fightNumber="
										+ $("#fightNumber").val());
					}

				});

		$("#btndelete").on(
				"click",
				function() {

					if (confirm('승부결과를 삭제하시겠습니까?')) {
						$(location).attr('href',
								"deleteFightResultBoard?no=" + $("#no").val());
					}

				});

	});
</script>
<style>
.th {
	background-color: #F8F8FF;
	font-weight: bold;
}
.td {
	min-height: 30px;
}

.table {
	font-size: 18px;
}
</style>

<title>승부결과</title>
</head>
<body>
<div class="content" >
	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
		<div class="col-lg-2 col-md-2 col-sm-3 col-xs-3 td th">글쓴이:</div>
		<div class="col-lg-4 col-md-4 col-sm-3 col-xs-9 td">${ frb.writer }</div>
		<div class="col-lg-2 col-md-2 col-sm-3 col-xs-3 th td">작성일:</div>
		<div class="col-lg-4 col-md-4 col-sm-3 col-xs-9 td">
			<fmt:formatDate value="${ frb.writeDate }" pattern="yy-MM-dd" />
		</div>
	</div>

	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
		<div class="col-lg-2 col-md-2 col-sm-3 col-xs-3 th td">대결날짜:</div>
		<div class="col-lg-4 col-md-4 col-sm-3 col-xs-9 td">
			<fmt:formatDate value="${ fight.fbResultDate }" pattern="yy-MM-dd" />
		</div>
		<div class="col-lg-2 col-md-2 col-sm-3 col-xs-3 th td">승자:</div>
		<div class="col-lg-4 col-md-4 col-sm-3 col-xs-9 td">${ frb.winner }</div>
	</div>

	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 td">
		<div class="col-lg-2 col-md-2 col-sm-3 col-xs-3 th td">참가자:</div>
		<div class="col-lg-8 col-md-8 col-sm-8 col-xs-9 td">${ fight.fbP1 }
			vs ${ fight.fbP2 }</div>
	</div>

	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 td">
		<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 th td">제목:</div>
		<div class="col-lg-10 col-md-10 col-sm-10 col-xs-9 td">${ frb.title }</div>
	</div>

	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 td">
		<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 th td">내용:</div>
		<div class="col-lg-10 col-md-10 col-sm-10 col-xs-9 td">
			<img src="resources/uploadimages/${ frb.photo }" width=200px /><br />
			${ frb.content }
		</div>
	</div>
</div>





	<!-- 모달 -->
	<div class="modal fade" id="denyMessageForm" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h5 class="modal-title" style="text-align: center;">반려 메시지를
						입력해주세요</h5>
					<p style="visibility: hidden;">1</p>
					<form id="denyResultMessageForm" class="form-horizontal"
						role="form" action="denyFightResult" method="post">
						<div class="form-group">
							<div class="col-sm-12 col-sm-12">
								<textarea class="form-control" name="message" id="message"
									rows="10" cols="30"></textarea>
								<input type="hidden" value="${ frb.no }" id="no" name="no" /> <input
									type="hidden" value="${ frb.writer }" name="writer" id="writer" />
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














