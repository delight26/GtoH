<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
    function content(pNo){
    		$('#productmodal').modal({
    		remote : 'productcontent?pNo='+pNo		
    		});
    }
    function updateproduct(pProductCode){
    	document.location.href="productupdate?pProductCode="+pProductCode;
    }
    function deleteproduct(pProductCode){
    	document.location.href="productdelete?pProductCode="+pProductCode;
    }
    </script>
<style>
#product {float: left;}
</style>
<c:set var="nickName" value="admin"/>
	<c:if test="${loginUser.nickName == nickName}">
		<a href="productadd">상품추가</a>
	</c:if>
	<form action="productbuy">
	<div id="product">
		<table>
			<c:forEach var="p" items="${pList}">
				<tr>
					<td><a href="productcontent?pNo=${p.pProductCode}" data-toggle="modal" data-target="#productmodal"><img src="resources/uploadimages/${p.pImage }" width="150px"/></a></td>
				</tr>
				<tr>
					<td>
						<p>${p.pName}</p>
						<p><img src="resources/images/point.png" width="20px"/>${p.pPrice}</p>
					</td>
					<c:set var="nickName" value="admin" />
				</tr>
				<c:if test="${loginUser.nickName == nickName}">
				<tr><td>
				<a href="javascript:updateproduct(${p.pProductCode})">수정</a>
				<a href="javascript:deleteproduct(${p.pProductCode})">삭제</a></td></tr>
   				 </c:if>
			</c:forEach>
			<tr>
				<td colspan="6" class="listPage">
					<c:if test="${ startPage > PAGE_GROUP }">
						<a href="productlist?pageNum=${ startPage - PAGE_GROUP }">[이전]</a>
					</c:if>			
				<c:forEach var="i" begin="${ startPage }" end="${ endPage }">
					<c:if test="${ i == currentPage }">
						[${ i }]
					</c:if>			
				<c:if test="${ i != currentPage }">
					<a href="productlist?pageNum=${ i }">[${ i }]</a>
				</c:if>			
			</c:forEach>
			<c:if test="${ endPage < pageCount }">
				<a href="productlist?pageNum=${ startPage + PAGE_GROUP }">[다음]</a>
			</c:if>		
		</td>	
	</tr>
	</table>
</div>
	</form>
	<div class="container">

  <!-- Modal -->
  <div class="modal fade" id="productmodal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        
      </div>
      
    </div>
  </div>
</div>