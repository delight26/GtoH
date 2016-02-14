<%@page import="com.project.call.domain.PointProduct"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="resources/js/productcontent.js"></script>
<style>
.prodInfo {
	padding-left: 30px;
	color: gray;
}
.b {
	color: black;
}
.prodInfo span {
	color: red; 
	font-size: 25px;
	font-weight: bold;
}
</style>
</head>
<body>
<form action="buyproduct" name="contentform">
	<div class="modal-header" style="background: #E4E3F3; color: #7092BE; border-radius: 4px">
			<button type="button" class="close" data-dismiss="modal">&times;</button>
			<h3 class="modal-title">상품 상세보기</h3>
	</div>
		<table style="margin: 30px">
			<tr>
				<td rowspan="7"><img style="width: 260px; height: 260px"
					src="resources/uploadimages/${prod.pImage }"/>
					<input type="hidden" name="pProductCode" value="${prod.pProductCode }"/></td>
				<td colspan="2" style="font-size: 24px">&nbsp;&nbsp;<b>${prod.pName}</b></td>
			</tr>
			<tr>
				<td class="prodInfo" colspan="2"><b class="b">사용포인트 : </b><span>${prod.pPrice}</span> point</td>
			</tr>
			<tr>
				<td class="prodInfo" colspan="2"><b class="b">재고수량 : </b>현재 총 <span>${prod.pAmount}</span> 개</td>
			</tr>
			<tr>
				<td class="prodInfo" rowspan="2" width="150px"><b class="b">구매 수량 : </b><input type="text" id="quantity" name="quantity"
					readonly style="width: 40px; text-align: center; color: black;"/></td>
				<td><img src=resources/images/btn_plus.gif onclick="plus()"/></td>
			</tr>
			<tr>
				<td><img src=resources/images/btn_minus.gif onclick="minus()"/></td>
			</tr>
			<tr>
				<td style="padding-left: 20px"><input type="button" class="btn btn-info btn-block-sm" onclick="buysubmit('${loginUser.email }', '${prod.pProductCode }','pcontent')" value="바로 구매하기" /></td>
				<td><input type="button" class="btn btn-warning btn-block-sm" onclick="addcart(${prod.pProductCode })" value="장바구니 담기"></td>
			</tr>
		</table>
	</form>
</body>
</html>