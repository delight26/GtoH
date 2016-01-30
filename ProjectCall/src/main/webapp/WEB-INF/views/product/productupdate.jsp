<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function back(){
	history.back();
}
</script>
</head>
<body>
	<form action="productupdateresult" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<th>상품명</th>
				<td><input type="text" name="pname" value="${prod.pName }"/></td>
			</tr>
			<tr>
				<th>가격</th>
				<td><input type="text" name="price" value="${prod.pPrice }"/></td>
			</tr>
			<tr>
				<th>수량</th>
				<td><input type="text" name="amount" value="${prod.pAmount }"/></td>
			</tr>
			<tr>
				<th>사진</th>
				<td><input type="file" name="image" /></td>
			</tr>
		</table>
		<input type="hidden" name="pProductCode" value="${prod.pProductCode }" />
		<input type="submit" value="수정하기" />
		<input type="button" value="취소" onclick="back()"/>
	</form>
</body>
</html>