<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
<title>회원정보 변경</title>
</head>
<body>
	<form>
		<table>
			<tr>
				<th>이메일</th>
				<td>${ member.email }</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="text" name="Password" /></td>
			</tr>
			<tr>
				<th>비밀번호 확인</th>
				<td><input type="text" name="checkPassword" /></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="text" name="checkPassword" value="${ member.name }" /></td>
			</tr>
			<tr>
				<th>별명</th>
				<td><input type="text" name="checkPassword" value="${ member.nickname }" /></td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td><input type="text" name="checkPassword" value="${ member.phone }" /></td>
			</tr>
		</table>
	</form>
	
	
	
	
	
	
	
	
	
</body>
</html>














