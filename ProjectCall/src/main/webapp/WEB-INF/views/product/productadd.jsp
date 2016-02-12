<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript">
function back(){
	history.back();
}

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
<style>
#addProd {
	margin: 30px;
}
.addTd {
	padding: 0 10px;
	font-weight: bold;
}
#UploadedImg {
	border: 4px dotted #EAEAEA;
	border-radius: 3px;
}
</style>
</head>
<body>
	<form action="productaddresult" method="post" enctype="multipart/form-data" id="productAdd">
		<div class="modal-header" style="background: #E4E3F3; color: #7092BE;">
			<button type="button" class="close" data-dismiss="modal">&times;</button>
			<h3 class="modal-title">상품 정보 입력</h3>
		</div>
		<table id="addProd">
			<tr>
				<td rowspan="4">
					<img id="UploadedImg" src="${pageContext.request.contextPath}/resources/images/no_image.jpg" width="200" height="200"/>
				</td>
				<td class="addTd">상품명</td>
				<td><input type="text" name="pname" /></td>
			</tr>
			<tr>
				<td class="addTd">포인트</td>
				<td><input type="text" name="price" /> point</td>
			</tr>
			<tr>
				<td class="addTd">수량</td>
				<td><input type="text" name="amount" /></td>
			</tr>
			<tr>
				<td class="addTd">사진</td>
				<td><input type="file" name="image" onchange="readURL(this);"/></td>
			</tr>
		</table>
		<div style="padding:0 0 20px 230px">
			<button type="button" onclick="javascript:productAdd.submit();" class="btn btn-warning btn-block-sm" data-dismiss="modal">등록</button>
			<button type="button" class="btn btn-info btn-block-sm" data-dismiss="modal">취소</button>
		</div>
	</form>
</body>
</html>