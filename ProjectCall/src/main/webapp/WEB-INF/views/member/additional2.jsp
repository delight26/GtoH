<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="resources/js/jquery-1.11.3.min.js"></script>
<script>
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
<title>추가정보입력</title>
<style>
body {
	width: 600px;
	margin: 0 auto;
	background:#f5f6f7;
}
#b {
	font-size: 10px;
	text-align: right;
	margin-top: 50px;
	margin-bottom: 20px;
}
#addInfo {
	width: 460px;
	margin: 0 auto;
	background-color: #fff;
	border:solid 1px #dadada;
	padding: 20px;
}
#addInfo td {
	padding: 5px;
	font-size: 12px;
	font-family: '맑은 고딕';
	height: 60px;
}
.phone {
	width: 50px;
}
.widetd {
	width: 170px;
	height: 60px;
}
#imgtd {
	width: 100px;
}
#add_btn {
	width: 460px;
	margin: 20px auto;
}
#UploadedImg {
	border-radius: 3px;
	border: 2px dotted #EAEAEA;
}
</style>
</head>
<body>
<div id="b">
	이용약관 > 회원가입 > <b>추가정보</b>
</div>
	<form action="step4" enctype="multipart/form-data" method="post">
		<table id="addInfo">
			<tr>
				<td class="widetd">선호지역
					<select id="area" name="area">
						<c:forEach items="${areaList}" var="area">
							<option>${area.area}</option>
						</c:forEach>
					</select>
				</td>
				<td rowspan="3" id="imgtd">
					<img id="UploadedImg" src="resources/images/no_image.jpg" width="122" height="145"/>
				</td>
			</tr>
			<tr>
				<td class="widetd">상세주소
					<input type="text" name="address" id="address" placeholder="시/군/구">
				</td>
			</tr>
			<tr>
				<td class="widetd">전화번호
					<select class="phone" name="phone2">
						<option>010</option>
						<option>011</option>
						<option>016</option>
						<option>019</option>
					</select>-<input type="text" name="phone1" class="phone">-<input type="text" name="phone3" class="phone">
				</td>
			</tr>
			<tr>
				<td colspan="2">프로필 사진 등록<br/>
					<input type="file" onchange="readURL(this);" name="photo" id="photo">
				</td>
			</tr>
			<tr>	
				<td colspan="2">남기고 싶은 말<br/>
					<textarea name="word" id="word" rows="2" cols="60"></textarea>
				</td>
			</tr>
			<tr>
				
			</tr>
		</table>
		<div id="add_btn">
			<a href="home"><img src="resources/images/btn_back.gif"/></a>
			<input type="image" value="입력" src="resources/images/btn_join.gif"/>
		</div>
	</form>
</body>
</html>