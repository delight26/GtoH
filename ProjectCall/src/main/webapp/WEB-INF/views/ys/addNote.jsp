<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <% int again = (int)request.getAttribute("again");   %>
<!DOCTYPE html >
<html>
<head>
<meta content="charset=UTF-8">
<title>쪽지보내기</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
<%if(again == 1){%>
window.close();
<%
}else{
	
}
 %>
</script>
<style>
#note td {width:100px; padding:2px}
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
<tr><td colspan="2">쪽지 보내기</td></tr>
<tr><td colspan="2">받는이 : <b>${ param.nickName }</b></td></tr>
<tr><td>제목</td><td><input type="text" name="title" style="width:345px;"/></td></tr>
<tr><td>내용</td><td><textarea name="content"rows="9" cols="60" scrollbars="no"></textarea></td></tr>
</table>
<div style="float:right"><input type="submit" value="보내기" onsubmit="return aa()"/></div>  
</form>
</body>
</html>