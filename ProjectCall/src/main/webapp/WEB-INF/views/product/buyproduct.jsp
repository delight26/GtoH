<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="resources/js/cartlist.js"></script>
</head>
<form action="cartorder" name="form1">
			<table id="cartTable" class="table">
				<tr>
					<th width="5%">선택</th>
					<th colspan="2">상품정보</th>
					<th>판매가</th>
					<th>수량</th>
					<th>합계</th>
				</tr>
				<c:forEach var="p" items="${ pList }" varStatus="s">
				<tr id="datatr">
					<td width="5%">
					<input type="checkbox" value="${p.pProductCode }" name="checkbox" id="check${s.count }" checked onchange="changef(${s.count })" />
					</td>
					<td width="10%" ><img style="width: 80px; height: 80px" src="resources/uploadimages/${p.pImage }" /></td>
					<td width="30%">${p.pName }</td>
					<td width="10%">${p.pPrice } <input type="hidden" id="price${s.count }" value="${p.pPrice }" />
					<input type="hidden" id="count${s.last }" value="${s.count }" /></td>
					<td width="15%"><input type="text" id="quentity${s.count }" name="quantity${s.count }" value="${p.pQuantity }" readonly />
					<img src="resources/images/btn_plus.gif" onclick="plus(${s.count })" />
					<img src="resources/images/btn_minus.gif" onclick="minus(${s.count })" /></td>
					<td width="20%"><input type="text" id="result${s.count }" name="payprice" value="${p.pPrice * p.pQuantity }" readonly /></td>
				</tr>
				</c:forEach>
			</table>
			<table>
				<tr>
				<th>메일주소</th>
				<td><input type="text" value="${loginUser.email }"/>
				</td></tr>
				<tr>
				<th>상품합계포인트</th>
				<td><input type="text" id="payresult" name="payresult" readonly /></td>
				</tr>
				<tr>
				<th>회원님의 포인트</th>
				<td><input type="text" id="point" name="point" value="${loginUser.point - loginUser.usepoint}" readonly /></td>
				</tr>
				</table>
			<a href="javascript:buysubmit('${loginUser.email }')">구매하기</a>
			<a href="javascript:back()">돌아가기</a>
	</form>
</html>