<%@page import="com.project.call.domain.PointProduct"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script>
	
	function addCart(prod) {
		var quentity = $('input:text[name=quentity]').val();
		if (quentity == "") {
			alert('구매 수량을 작성하셔야 합니다.');
		} else {
			var url = "addcart?quentity=" + quentity + "&prod=" + prod;
			var popOption = "width=370, height=360, resizable=no, scrollbars=no, status=no";
			window.open(url, "장바구니",popOption);
		}
	}
	
	function plus() {
		var quentity = $('#quentity').val();
		quentity = Number(quentity) + 1;
		$('#quentity').val(Number(quentity));
	}
	
	function minus() {
		var quentity = $('#quentity').val();
		if (quentity <= 1) {
			alert('최소 한개 이상은 구매 하셔야 합니다.');
		} else {
			quentity = Number(quentity) - 1;
			$('#quentity').val(Number(quentity));
		}
	}

	$(document).ready(function() {
		$('#quentity').val(1);
	});
</script>
</head>
<body>
	<table>
		<tr>
			<td><img style="width: 400px; height: 400px" src="resources/uploadimages/${prod.pImage }" /></td>
			<td>${prod.pName }<br />
			  	판매가격 : ${prod.pPrice }<br /><br />
				구매수량 : <input type="text" id="quentity" name="quentity" readonly style="width: 40px" />
				<div style="padding: 1 0 2 0">
					<img src=resources/images/btn_plus.gif onclick="plus()" />
				</div>
				<div style="padding: 1 0 2 0">
					<img src=resources/images/btn_minus.gif onclick="minus()" />
				</div><br />
				<a href="javascript:addCart(${prod.pNo })">구매하기</a>
				<a href="javascript:addCart(${prod.pNo })">장바구니</a>
		</tr>
	</table>
</body>
</html>