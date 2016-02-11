<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.project.call.domain.*, java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
  	String email = (String)session.getAttribute("email");
  
   boolean loginUser = false;
	if (email == null) {
		loginUser = false;
	} else {
		loginUser = true;
	}  
%>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="resources/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
 	 function writeForm() {
		 if (<%=loginUser%>) {
			 window.location.href="writeForm"
		} else {
			alert("로그인하세요");
			window.location.href="loginform"
		}
	}  
</script>
</head>
<body>
	<table>
		<tr>
			<th>글 번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>등록일</th>
			<th>조회 수</th>
		</tr>

		<c:forEach var="frb" items="${frbList }">
			<tr>
				<td>${frb.frbNo }</td>
				<td><a href="FreeBoardContent?frbNo=${frb.frbNo }">
						${frb.frbTitle }</a></td>
				<td>${frb.frbWriter }</td>
				<td>${frb.frbWriteDate }</td>
				<td>${frb.frbHit}</td>
			</tr>
		</c:forEach>

		<!-- 	<tr>
			<td><input type="hidden" name="area" value="choice" /></td>
			</tr> -->
			
			<tr>
	<td><input type="button" name="write" value="글쓰기" 
			onclick="window.location.href='writeForm'" /></td>
	<td><input type="button" name="AllList" value="목록보기"
			onclick="window.location.href='FreeBoardList'" /></td>
	
	</tr>
	</table>
</body>
</html>