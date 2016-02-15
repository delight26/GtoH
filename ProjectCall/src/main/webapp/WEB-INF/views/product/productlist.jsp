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
		$.ajax({
	        url: "productupdate",
	        type:"GET",
	        data: {"pProductCode" : pProductCode},
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
	/* document.location.href = "productupdate?pProductCode=" + pProductCode; */

	
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
</style>
<div style="border-bottom: 4px solid #7092BE; margin: 30px 0"><span style="font-size: 48px"><b>GH포인트몰</b></span> <span>획득한 포인트로 상품을 구매하실 수 있습니다.</span></div>
<c:set var="nickName" value="admin" />
<c:if test="${loginUser.nickName == nickName}">
	<div><a href="productadd" data-toggle="modal" data-target="#myModal"><img src="resources/images/addProduct.gif" width="70px" style="border-radius: 4px; float: right"/></a></div>
</c:if>
<div>
<c:forEach var="p" items="${pList}">
	<form action="productbuy">
		<div id="product">
			<table>
				<tr>
					<td><a href="javascript:prodcontent('${p.pProductCode}')" ><img
							src="resources/uploadimages/${p.pImage }" height="180px" style="margin: 0 15px;" /></a></td>
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
						<td><a href="javascript:updateproduct('${p.pProductCode}')" style="float: right"><img src="resources/images/btn_modify.gif" width="40px" style="border-radius: 4px; margin-right:2px"/></a>
							<a href="javascript:deleteproduct('${p.pProductCode}')" style="float: right"><img src="resources/images/btn_delete.gif" width="40px" style="border-radius: 4px; margin-right:2px"/></a></td>
					</tr>
				</c:if>
			</table>
		</div>
	</form>
</c:forEach>
</div>
<div style="clear: both">
	<c:if test="${ startPage > PAGE_GROUP }">
		<ul class="pager">
			<li><a href="productlist?pageNum=${ startPage - PAGE_GROUP }">[이전]</a></li>
		</ul>
	</c:if>
	<div class="text-center">
		<ul class="pagination">
			<c:forEach var="i" begin="${ startPage }" end="${ endPage }">
				<c:if test="${ i == currentPage }">
					<li class="disabled"><a href="#">${ i }</a></li>
				</c:if>
				<c:if test="${ i != currentPage }">
					<li><a href="productlist?pageNum=${ i }">${ i }</a></li>
				</c:if>
			</c:forEach>
		</ul>
	</div>
	<c:if test="${ endPage < pageCount }">
		<ul class="pager">
			<li><a href="productlist?pageNum=${ startPage + PAGE_GROUP }">[다음]</a></li>
		</ul>
	</c:if>
</div>