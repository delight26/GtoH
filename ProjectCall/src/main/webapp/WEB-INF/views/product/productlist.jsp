<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
	function content(pNo) {
		$('#productmodal').modal({
			remote : 'productcontent?pNo=' + pNo
		});
	}
	function updateproduct(pProductCode) {
		document.location.href = "productupdate?pProductCode=" + pProductCode;
	}
	function deleteproduct(pProductCode) {
		var conf = confirm("상품을 삭제 하시겠습니까?");
		if(conf) {
			document.location.href = "productdelete?pProductCode=" + pProductCode;
		}
	}
</script>
<style>
#product {
	float: left;
	border-right: 1px solid #EAEAEA;
}
#page {
	clear: both;
}
</style>
<img src="resources/images/pointmall3.jpg" width="100%" style="margin-bottom:20px;"/>
<c:set var="nickName" value="admin" />
<c:if test="${loginUser.nickName == nickName}">
	<a href="productadd" data-toggle="modal" data-target="#myModal"><img src="resources/images/addProduct.gif" width="70px" style="border-radius: 4px; float: right"/></a>
</c:if>
<c:forEach var="p" items="${pList}">
	<form action="productbuy">
		<div id="product">
			<table>
				<tr>
					<td><a href="productcontent?pNo=${p.pProductCode}"
						data-toggle="modal" data-target="#productmodal"><img
							src="resources/uploadimages/${p.pImage }" width="180px" style="margin: 0 15px" /></a></td>
				</tr>
				<tr>
					<td>
						<p style="margin: 0 15px">${p.pName}</p>
						<p>
							<img src="resources/images/point.png" width="20px" style="margin: 0 0 0 15px" />${p.pPrice}</p>
					</td>
					<c:set var="nickName" value="admin" />
				</tr>
				<c:if test="${loginUser.nickName == nickName}">
					<tr>
						<td><a href="javascript:updateproduct(${p.pProductCode})" style="float: right"><img src="resources/images/btn_modify.gif" width="40px" style="border-radius: 4px; margin-right:2px"/></a>
							<a href="javascript:deleteproduct(${p.pProductCode})" style="float: right"><img src="resources/images/btn_delete.gif" width="40px" style="border-radius: 4px; margin-right:2px"/></a></td>
					</tr>
				</c:if>
			</table>
		</div>
	</form>
</c:forEach>
<div id="page">
<table>
	<tr>
		<td colspan="6" class="listPage"><c:if
				test="${ startPage > PAGE_GROUP }">
				<a href="productlist?pageNum=${ startPage - PAGE_GROUP }">[이전]</a>
			</c:if> <c:forEach var="i" begin="${ startPage }" end="${ endPage }">
				<c:if test="${ i == currentPage }">
						[${ i }]
					</c:if>
				<c:if test="${ i != currentPage }">
					<a href="productlist?pageNum=${ i }">[${ i }]</a>
				</c:if>
			</c:forEach> <c:if test="${ endPage < pageCount }">
				<a href="productlist?pageNum=${ startPage + PAGE_GROUP }">[다음]</a>
			</c:if></td>
	</tr>
</table>
</div>



<div class="container">

	<!-- Modal -->
	<div class="modal fade" id="productmodal" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content"></div>

		</div>
	</div>
</div>