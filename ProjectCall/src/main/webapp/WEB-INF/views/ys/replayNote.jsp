<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html>
<head>
<meta content="charset=UTF-8">
<title>쪽지 답장하기</title>
<script type="text/javascript" src="resources/js/jquery-1.11.3.min.js"></script>
<style>
#note td {padding:2px}
#note {border: 5px solid #BCE55C;}
body {
	background:#BCE55C;
	font-size: 12px;
	font-family:'맑은 고딕'
}
input, textarea {
	background:#F6FFCC;
	font-size: 12px;
	font-family:'맑은 고딕'
}
</style>
</head>
<body>
<form action="YSaddNote" name="form1" id="form1">
<!--보낸사람 -세션처리 -->
<input type="hidden" name="email" value="${loginUser.email }">
<!--받는사람  -->
<input type="hidden" name="toid" value="${ param.nickName}">
<table id="note">
<tr><td colspan="2" style="font-size: 14px"><b>쪽지 답장하기</b></td></tr>
<tr><td style="width: 90px">받는이</td><td><input type="text" value="${ param.nickName }" readonly/></td></tr>
<tr><td>제목</td><td><input type="text" name="title" style="width:300px;"/></td></tr>
<tr><td>내용</td><td><textarea name="content"rows="10" cols="51" scrollbars="no"></textarea></td></tr>
</table>
<div style="float:right; margin-top: 20px">
	<input type="submit" value="보내기">  
	<input type="button" onclick="javascript:history.back();" value="취소">
</div>
</form>
</body>
</html>