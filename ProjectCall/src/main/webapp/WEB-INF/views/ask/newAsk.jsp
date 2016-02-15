<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/createXhr.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/idCheck.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/autoSearch.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/agrrowrite.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<div class="modal-content">
<form action="addAsk" method="post">
	<div class="modal-header"
		style="background: #E4E3F3; color: #7092BE; text-align: center; border-radius: 4px">
		<button type="button" class="close" data-dismiss="modal">&times;</button>
		<h3 class="modal-title">대결 신청서</h3>
	</div>
	<div class="modal-body">
		<div class="form-group">
			<label for="title" class="col-lg-2 control-label">신청자</label>
			<div class="col-lg-9">
				<input type="text" class="form-control"
					value="${loginUser.nickName }" />
			</div>
		</div>
		<div class="form-group">
			<label for="title" class="col-lg-2 control-label">대결 상대</label>
			<div class="col-lg-9">
				<input type="text" class="form-control" id="toId" name="toId"
					placeholder="닉네임 검색">
				<div id="listBox">
					<ul id="resultList"></ul>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="title" class="col-lg-2 control-label">대결 날짜</label>
			<div class="col-lg-9">
				<input type="date" class="form-control" id="fightDate"
					name="fightDate" placeholder="yyyy-mm-dd" />
			</div>
		</div>
		<div class="form-group">
			<label for="title" class="col-lg-2 control-label">대결 장소</label>
			<div class="col-lg-9">
				<input type="text" class="form-control" id="place" name="place"
					placeholder="대결요청장소">
			</div>
		</div>
		<div class="form-group">
			<label for="title" class="col-lg-2 control-label">메세지</label>
			<div class="col-lg-9">
				<input type="text" class="form-control" id="tell" name="tell"
					placeholder="전하고 싶은말!!">
			</div>
		</div>
	</div>
	<div class="modal-footer">
		<div style="text-align:center; margin-top: 20px">
			<input type="submit" value="신청" class="btn btn-info" /> <input
				type="button" class="btn btn-default" data-dismiss="modal"
				value="취소" />
		</div>
	</div>
</form>
</div>
</body>
</html>