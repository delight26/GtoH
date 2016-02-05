<%@page import="com.project.call.domain.PointProduct"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/productcontent.js"></script>
<script type="text/javascript">
function updateproduct(pProductCode){
	document.location.href="productupdate?pProductCode="+pProductCode;
}
function deleteproduct(pProductCode){
	document.location.href="productdelete?pProductCode="+pProductCode;
}
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
				<a href="javascript:updateproduct(${prod.pProductCode })">수정하기</a>
				<a href="javascript:deleteproduct(${prod.pProductCode })">삭제하기</a>
				<a href="javascript:history.back()">목록보기</a>
		</tr>
	</table>
</body>
</html>