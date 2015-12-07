<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.bookmanage.member.*, com.bookmanage.dao.*"%>
<%@ page import="java.util.*" %>
<%
Book book = (Book)request.getAttribute("book");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function bookList(){
	location.href = "booklist.do";
}
</script>
</head>
<body>
<form action="bookupdateResult.do" enctype="multipart/form-data" method="post">
<input type="hidden" name="bookNumber" value="<%=book.getBookNumber()%>">
도서명 : <input type="text" name = "bookName" value="<%=book.getBookName()%>"/><br/>
작가 : <input type="text" name = "author" value="<%=book.getAuthor()%>"/><br/>
출판사 : <input type="text" name ="bookCompany" value="<%=book.getBookCompany()%>"/><br/>
가격 : <input type="text" name = "Price" value="<%=book.getPrice()%>"/><br/>
사진 : <input type="file" name ="image" value="<%=book.getPhoto()%>"/><br/>
<input type="submit" value="수정 하기"/>
<input type="button" value="취소" onclick="bookList()"/>
</form>
</body>
</html>