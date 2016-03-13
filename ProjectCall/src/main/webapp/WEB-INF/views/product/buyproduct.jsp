<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/cartlist.js"></script>
<style>
#cartTable th {
	background: #E4E3F3;
	text-align: center;
}
#cartTable td {text-align:center;}
#cartbottom {
	background: #f5f6f7;
	border-top: 1px solid #7092BE;
	border-bottom: 1px solid #7092BE;
	height: 120px;
}
#payresult {
	font-size: 40px;
	color: #DB3A00;
	background: #f5f6f7;
	border: none;
	width: 130px;
	text-align: right;
}
#cartTable .line {line-height: 80px}
.num {width: 40px;line-height:20px;text-align:center}
.num2 {width: 120px;line-height:20px;text-align:right}
#buyTable {width:100%;margin: 50px 0}
#buyTable td {font-size: 16px;padding: 10px; border-bottom: 1px solid #eaeaea;}
#buyer {font-size: 30px;border-bottom: 2px solid gray;}
.info {color:gray; background: #f5f6f7; width: 200px}
</style>
<div style="border-bottom: 4px solid #7092BE; margin: 30px 0"><span style="font-size: 48px"><b>BUY IT</b></span> <span>구매하신 상품은 기프티콘으로 전송됩니다.</span></div>
<form action="cartorder" name="form1">
	<table id="cartTable" class="table">
		<tr>
			<th width="5%">선택</th>
			<th colspan="2">상품정보</th>
			<th>구매포인트</th>
			<th colspan="2">수량</th>
			<th>합계</th>
		</tr>
		<c:forEach var="p" items="${ pList }" varStatus="s">
			<tr id="datatr" class="product">
				<td class="line" width="5%" rowspan="2"><input type="checkbox"
					value="${p.pProductCode }" name="checkbox" id="check${s.count }"
					checked onchange="changef(${s.count })" /></td>
				<td class="line" rowspan="2" width="10%"><img
					style="width: 80px; height: 80px;"
					src="resources/uploadimages/${p.pImage }" /></td>
				<td class="line" width="40%" rowspan="2" id="prodName">${p.pName }</td>
				<td class="line" width="10%" rowspan="2">${p.pPrice }p<input
					type="hidden" id="price${s.count }" value="${p.pPrice }" /> <input
					type="hidden" id="count${s.last }" value="${s.count }" /></td>
				<td class="line" rowspan="2" style="padding-left: 40px"><input
					type="text" id="quentity${s.count }" name="quantity${s.count }"
					value="${p.pQuantity }" readonly class="num" /></td>
				<td><img src="resources/images/btn_plus.gif"
					onclick="plus(${s.count })" /></td>
				<td class="line" width="20%" rowspan="2"><input type="text"
					id="result${s.count }" name="payprice"
					value="${p.pPrice * p.pQuantity }" readonly class="num2" /></td>
			</tr>
			<tr>
				<td><img src="resources/images/btn_minus.gif"
					onclick="minus(${s.count })" /></td>
			</tr>
		</c:forEach>
	</table>
	<div id="buttonGroup" style="margin-bottom: 20px">
		<input type="button" onclick="checkdel()" class="btn btn-default btn-sm" value="선택상품 삭제" />
		<input type="button" onclick="del()" class="btn btn-default btn-sm" value="전체상품 삭제"/>
		<input type="button" onclick="back()" class="btn btn-default btn-sm" value="돌아가기" />
	</div>
	<div id="cartbottom">
		<table style="float: right; margin-top: 20px;">
			<tr>
				<th style="text-align: right;">합계
					포인트&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
			</tr>
			<tr>
				<td><b><span style="font-size: 35px">=</span></b><b>
				<input type="text" id="payresult" name="payresult" readonly /></b>
				<span style="font-size: 35px">P&nbsp;&nbsp;</span></td>
			</tr>
		</table>
	</div>
	<table id="buyTable">
		<tr><th id="buyer" colspan="2">구매자 정보</th></tr>
		<tr>
			<td class="info">이름</td>
			<td>${loginUser.name }</td>
		</tr>
		<tr>
			<td class="info">연락처</td>
			<td>${loginUser.phone }</td>
		</tr>
		<tr>
			<td class="info">이메일</td>
			<td>${loginUser.email }</td>
		</tr>
		<tr>
			<td class="info">현재 사용가능 포인트</td>
			<td>${loginUser.point - loginUser.usepoint} point</td>
		</tr>
	</table>
	<div style="text-align: center; margin-bottom: 100px">
		<input type="button" onclick="buyproduct('${loginUser.email }', '${loginUser.point - loginUser.usepoint}')" class="btn btn-info btn-lg" value="구매하기" />&nbsp;&nbsp;
		<input type="button" onclick="back()" class="btn btn-warning btn-lg" value="돌아가기" />
	</div>
</form>
