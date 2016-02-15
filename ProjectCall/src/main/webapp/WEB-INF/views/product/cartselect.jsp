<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니 담기</title>
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
<img src="resources/images/add_basket.jpg"/>
<div style="text-align: center;">
	<input type="button" onclick="getcartlist()" class="btn btn-default btn-sm" value="장바구니 이동"/>
	<input type="button" onclick="shopping()" class="btn btn-default btn-sm" value="쇼핑 계속하기"/>
</div>
</body>
</html>