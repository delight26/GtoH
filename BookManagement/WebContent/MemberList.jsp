<%@page import="com.bookmanage.member.*, java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function deleteMember(id){
	location.href = "memberdelete.do?id="+id;
}
</script>
</head>
<body>
	<table>
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>전화 번호</th>
			<th>생일</th>
			<th>직업</th>
			<th>주소</th>
			<th>삭제</th>
		</tr>

	<c:forEach var="member" items="${memberList}">
			<tr>
				<td>${member.id}</td>
				<td>${member.name}</td>
				<td>${member.phone}</td>
				<td>${member.birthday}</td>
				<td>${member.job}</td>
				<td>${member.adress}</td>
				<td><input type = "button" name="btnDelete" value="삭제" onclick = "deleteMember('${member.id}')"/>
			</tr>
		</c:forEach>
	</table>
</body>
</html>


