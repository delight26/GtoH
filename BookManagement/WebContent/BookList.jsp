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
function readBook(bookNumber) {
	location.href = "bookRead.do?bookNumber=" + bookNumber;
}
function SerachDepartment(){
	var Name = document.getElementById('bookSearch').value;;
	location.href = "bookSearch.do?bookSearch="+Name;
}
function insertBook() {
	location.href = "bookInsert.do";
}
function updateBook(bookNumber) {
	location.href = "bookUpdate.do?bookNumber=" + bookNumber;
}
function deleteBook (BookNumber){
	var conf = confirm("정말 삭제 하시겠습니까?");
	if(conf){
		location.href="bookdelete.do?BookNumber="+BookNumber;
	}
}
function SerachNotice(){
	var Name = document.getElementById('searchName').value;
	location.href = "BookSerach.do?searchName="+Name;
}
</script>
</head>
<body>
<input type="text" name="searchName" id="searchName"/>
<input type="button" value="검색하기" onclick="SerachNotice()"/>
<input type="button" value="도서등록" onclick="insertBook()"/>
	<table>
		<tr>
			<th>도서번호</th>
			<th>도서명</th>
			<th>작가</th>
			<th>출판사</th>
			<th>가격</th>
			<th>삭제</th>
			<th>수정</th>
		</tr>

	<c:forEach var="book" items="${bookList}">
			<tr>
				<td>${book.bookNumber}</td>
				<td><a href="javascript:readBook('${book.bookNumber}')">${book.bookName}</a></td>
				<td>${book.author}</td>
				<td>${book.bookCompany}</td>
				<td>${book.price}</td>
				<td><input type = "button" name="btnDelete" value="삭제" onclick = "deleteBook('${book.bookNumber}')"/></td>
				<td><input type = "button" name="btnDelete" value="수정" onclick = "updateBook('${book.bookNumber}')"/></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>