<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/createXhr.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/find.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<div class="modal-content">
	<form action="">
	<div class="modal-header" style="background: #E4E3F3; color: #7092BE; text-align: center; border-radius: 4px">
		<button type="button" class="close" data-dismiss="modal">&times;</button>
		<h4 class="modal-title"><b>비밀번호 찾기</b></h4>
	</div>
	<div class="modal-body">
		<div class="form-group">
			<p class="ptag">이름</p>
            <p>
				<input type="text"	class="form-control" name="name" id="name"/>
			</p>
		</div>
		<div class="form-group">
			<p class="ptag">생일</p>
            <p>
				<input type="text"	class="form-control" name="birthday" id="birthday" placeholder="yyyy-mm-dd"/>
			</p>
		</div>
		<div class="form-group">
			<input type="button" onclick="findid()" value="메일 검색" class="btn btn-info btn-block" />
		</div>
		<div id="getId"></div>
	</div>
			
	</form>
	<span id = "findCheckLayer"></span>
</div>
</body>
</html>