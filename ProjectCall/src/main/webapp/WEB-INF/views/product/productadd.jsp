<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="productaddresult" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<th>상품명</th>
				<td><input type="text" name="pname" /></td>
			</tr>
			<tr>
				<th>가격</th>
				<td><input type="text" name="price" /></td>
			</tr>
			<tr>
				<th>수량</th>
				<td><input type="text" name="amount" /></td>
			</tr>
			<tr>
				<th>사진</th>
				<td><input type="file" name="image" /></td>
			</tr>
		</table>
	</form>
</body>
</html>