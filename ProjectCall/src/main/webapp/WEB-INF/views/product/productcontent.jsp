<%@page import="com.project.call.domain.PointProduct"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="resources/js/productcontent.js"></script>
</head>
<body>
<form action="buyproduct" name="form1">
	<table>
		<tr>
			<td><img style="width: 400px; height: 400px" src="resources/uploadimages/${prod.pImage }" /></td>
			<td>${prod.pName }<br />
			  	판매가격 : ${prod.pPrice }<br /><br />
				구매수량 : <input type="text" id="quentity" name="quantity" readonly style="width: 40px" />
				<div style="padding: 1 0 2 0">
					<img src=resources/images/btn_plus.gif onclick="plus()" />
				</div>
				<div style="padding: 1 0 2 0">
					<img src=resources/images/btn_minus.gif onclick="minus()" />
				</div><br />
				<input type="hidden" name="pProductCode" value="${prod.pProductCode }" />
				<a href="javascript:buysubmit(${loginUser.email })">구매하기</a>
				<a href="javascript:addcart(${prod.pProductCode })">장바구니</a>
				<a href="javascript:history.back()">목록보기</a>
		</tr>
	</table>
	</form>
</body>
</html>