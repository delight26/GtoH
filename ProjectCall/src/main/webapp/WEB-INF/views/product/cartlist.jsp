<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/cartlist.js"></script>
<style>
#cartTable th {
	background: #E4E3F3;
	text-align: center;
}
#cartTable td {
	text-align: center;
	height: 80px;
	line-height: 80px;
}
#prodName {text-align: left;}
</style>

<div style="border-bottom: 4px solid #7092BE; margin: 30px 0"><span style="font-size: 48px"><b>MY CART</b></span> <span>구매하신 상품은 기프티콘으로 전송됩니다.</span></div>
<form action="buycartproduct" name="form1">
	<table id="cartTable" class="table">
		<tr>
			<th width="5%">선택</th>
			<th colspan="2">상품정보</th>
			<th>구매포인트</th>
			<th>수량</th>
			<th>가격</th>
		</tr>
		<c:forEach var="p" items="${ pList }" varStatus="s">
		<tr id="datatr" class="product">
					<td width="5%">
					<input type="checkbox" value="${p.pProductCode }" name="checkbox" id="check${s.count }" checked onchange="changef(${s.count })" />
					</td>
					<td width="10%" ><img style="width: 80px; height: 80px" src="resources/uploadimages/${p.pImage }" /></td>
					<td width="30%" id="prodName">${p.pName }</td>
					<td width="10%">${p.pPrice } <input type="hidden" id="price${s.count }" value="${p.pPrice }" />
					<input type="hidden" id="count${s.last }" value="${s.count }" /></td>
					<td width="40px"><input type="text" id="quentity${s.count }" name="quantity${s.count }" value="${p.pQuantity }" readonly />
					<img src="resources/images/btn_plus.gif" onclick="plus(${s.count })" />
					<img src="resources/images/btn_minus.gif" onclick="minus(${s.count })" /></td>
					<td width="20%"><input type="text" id="result${s.count }" name="payprice" value="${p.pPrice * p.pQuantity }" readonly /></td>
		</tr>
		</c:forEach>
	</table>
			<div id="cartbottom">
				<div id="price">
				<div id="pc"><b><input type="text" id="payresult" name="payresult" readonly /></b></div><div id="p">상품합계금액</div>
				</div>
			</div>
			<div id="buttonGroup">
			<a href="javascript:cartsubmit('${loginUser.email }','cart')">구매하기</a>
			<a href="javascript:checkdel()">삭제하기</a>
			<a href="javascript:del()">모두삭제</a>
			<a href="javascript:back()">돌아가기</a>
	</div>
</form>