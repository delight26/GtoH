<%@page import="com.bookmanage.member.*, java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#div{
float:left;
}
</style>
<script>
function SerachNotice(){
	var Name = document.getElementById('searchName').value;
	location.href = "BookSerach.do?searchName="+Name;
}
function readBook(bookNumber) {
	location.href = "bookRead.do?bookNumber=" + bookNumber;
}
</script>
</head>
<body>
<input type="text" name="searchName" id="searchName"/>
<input type="button" value="검색하기" onclick="SerachNotice()"/>
<br/><br/>
<c:forEach var="book" items="${bookList}">
	<div id="div">
	<table>
			<tr>
				<th>${book.bookName}</th>
			<tr>
			<tr>
				<td class="image">
			<a href="javascript:readBook('${book.bookNumber}')">
			<img
					src="${book.photo }" width = "250px" height="250px" />
					</a>
				</td>
			</tr>
			<tr>
				<td class="info">
				작가: ${book.author}<br/>
				출판사 : ${book.bookCompany}<br />
				판매가 : ${book.price}<br /> 
				도서 번호 : ${book.bookNumber}<br />
				</td>
			</tr>
	</table>
	</div>
	</c:forEach>
</body>
</html>