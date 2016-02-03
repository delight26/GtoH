<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
    function content(pNo){
    		$('#productmodal').modal({
    		remote : 'productcontent?pNo='+pNo		
    		});
    }
    </script>
</head>
<body>
	<form action="productbuy">
		<table>
			<c:forEach var="p" items="${pList }">
				<tr>
					<td><a href="productcontent?pNo=${p.pProductCode }" data-toggle="modal" data-target="#productmodal"><img src="resources/uploadimages/${p.pImage }" /></a></td>
				</tr>
				<tr>
					<td>${p.pName }</td>
				</tr>
				<tr>
					<td>${p.pPrice }</td>
				</tr>
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
</body>
</html>