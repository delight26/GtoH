<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function shopping(){
	self.close();
}
function getcartlist(){
	opener.document.location.href="getcartlist";
	self.close();
}
</script>
</head>
<body>
<a href="javascript:getcartlist()">장바구니로</a>
<a href="javascript:shopping()">계속 쇼핑하기</a>
</body>
</html>