<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <% int again = (int)request.getAttribute("again");   %>
<!DOCTYPE html >
<html>
<head>
<meta content="charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="resources/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
<%if(again == 1){%>
window.close();
<%
}
 %>
</script>
</head>
<body>

<form action="YSaddNote" name="form1" id="form1">
<h3>${ param.nikname }님에게 쪽지</h3>
<!--보낸사람 -세션처리 -->
<input type="hidden" name="email" value="aa@naver.com">
<!--받는사람  -->
<input type="hidden" name="toid" value="${ param.email}">
<table>
<tr><th>제목</th><td><input type="text" name="title"></td></tr>
<tr><th>내용</th><td><textarea name="content"rows="20" cols="20"></textarea></td></tr>
</table>
<input type="submit" value="쪽지보내기" onsubmit="return aa()" >  
<input type="button" onclick="window.close()"value="닫기">
</form>
</body>
</html>