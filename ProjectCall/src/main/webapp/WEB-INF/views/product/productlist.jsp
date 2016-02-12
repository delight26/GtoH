<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="resources/js/productcontent.js"></script>
<script>
	function prodcontent(pNo){
		$.ajax({
	        url: "productcontent",
	        type:"GET",
	        data: {"pNo" : pNo},
	        dataType: "text",
	        success: function(responseData, statusText, xhr){
	        	var result = responseData;
	        	$('#myModal').modal({
	        		remote : $('.modal-content').html(result)
	        		});
	        },
	        error : function(xhr, statusText, responseData){
	           alert("error : " + statusText + "." + xhr.status+ "/ " + xhr.responseText);
	        }
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
					<td><a href="javascript:prodcontent('${p.pProductCode}')" ><img
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
