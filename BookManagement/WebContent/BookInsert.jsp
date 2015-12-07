<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<form action="bookInsertResult.do" enctype="multipart/form-data" method="post">
도서명 : <input type="text" name = "bookName"/><br/>
작가 : <input type="text" name = "author"/><br/>
출판사 : <input type="text" name ="bookCompany"/><br/>
가격 : <input type="text" name = "Price"/><br/>
사진 : <input type="file" name ="image"/><br/>
<input type="submit" value="등록 하기"/>
<input type="button" value="취소" onclick="bookList()"/>
</form>
</body>
</html>