<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/createXhr.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/find.js"></script>
<title>Insert title here</title>
</head>
<body>

	<form action="">
		<table>
			<tr>
				<th>이름 : </th>
				<td><input type="text" id="name" name="name" ></td>
			</tr>
			<tr>
				<th>생일 : </th>
				<td><input type="date" id="birthday" name="birthday"></td>
			</tr>
		</table>	
		<input type="button" onclick="find();" value="인증메일 보내기">
	</form>
	<span id = "findCheckLayer"></span>

</body>
</html>