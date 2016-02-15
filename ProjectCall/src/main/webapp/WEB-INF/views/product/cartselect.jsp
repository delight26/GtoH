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
<a href="javascript:getcartlist()"><img src="resources/images/다운로드.png"/></a>
<a href="javascript:shopping()"><img src="resources/images/12-tag-&-label-icons.jpg"/></a>
</body>
</html>