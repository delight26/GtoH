<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html >
<html>
<head>
<meta content="charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>
<table>
<tr><th>제목</th><td>${note.getNbTitle()}</td>
<th>작성일</th><td><fmt:formatDate value="${note.getNbDate()}" pattern="yyyy-MM-dd" /></td></tr>
<tr><th>보낸이</th><td>${note.getNbNickName()}</td></tr>
<tr><th>내용</th><td>${note.getNbContent()}</td></tr>
</table>
<input type="button" value="답장">
<input type="button" value="삭제" 
onclick="window.location.href='YSdeleteNote?nbNo=${note.getNbNo()}&toid=${note.getNbToid()}'">
</div>
</body>
</html>