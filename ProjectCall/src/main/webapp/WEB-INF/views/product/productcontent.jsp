<%@page import="com.project.call.domain.PointProduct"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="resources/js/productcontent.js"></script>
</head>
<body>
<form action="buyproduct" name="contentform">
	<table>
		<tr>
			<td><img style="width: 400px; height: 400px" src="resources/uploadimages/${prod.pImage }" />
			<input type="hidden" name="pProductCode" value="${prod.pProductCode }"/></td>
			<td>${prod.pName }<br />
			  	판매가격 : ${prod.pPrice }<br /><br />
				구매수량 : <input type="text" id="quantity" name="quantity" readonly style="width: 40px" />
				<div style="padding: 1 0 2 0">
					<img src=resources/images/btn_plus.gif onclick="plus()" />
				</div>
				<div style="padding: 1 0 2 0">
					<img src=resources/images/btn_minus.gif onclick="minus()" />
				</div><br />
				<a href="javascript:buysubmit('${loginUser.email }', '${prod.pProductCode }','pcontent')">구매하기</a>
				<a href="javascript:addcart(${prod.pProductCode })">장바구니</a>
				<a href="javascript:history.back()">목록보기</a><br/>
				<c:set var="nickName" value="admin" />
				<c:if test="${loginUser.nickName == nickName}">
				<a href="javascript:updateproduct(${prod.pProductCode })">수정하기</a>
				<a href="javascript:deleteproduct(${prod.pProductCode })">삭제하기</a>
				</c:if>
		</tr>
	</table>
	</form>
</body>
</html>