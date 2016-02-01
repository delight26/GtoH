<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
	<form action="productbuy">
		<table>
			<c:forEach var="p" items="${pList }">
				<tr>
					<td><a href="adminproductcontent?pNo=${p.pProductCode }"><img src="resources/uploadimages/${p.pImage }" /></a></td>
					<td>${p.pName }</td>
					<td>${p.pPrice }</td>
					<td>${p.pPrice }</td>
					<td><input type="button" value="수정하기" onclick="updateproduct(${p.pProductCode })"/></td>
					<td><input type="button" value="삭제하기" onclick="deleteproduct(${p.pProductCode })"/></td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="6" class="listPage">
					<c:if test="${ startPage > PAGE_GROUP }">
						<a href="adminproductlist?pageNum=${ startPage - PAGE_GROUP }">[이전]</a>
					</c:if>			
				<c:forEach var="i" begin="${ startPage }" end="${ endPage }">
					<c:if test="${ i == currentPage }">
						[${ i }]
					</c:if>			
				<c:if test="${ i != currentPage }">
					<a href="adminproductlist?pageNum=${ i }">[${ i }]</a>
				</c:if>			
			</c:forEach>
			<c:if test="${ endPage < pageCount }">
				<a href="adminproductlist?pageNum=${ startPage + PAGE_GROUP }">[다음]</a>
			</c:if>		
		</td>	
	</tr>
		</table>
	</form>
</body>
</html>