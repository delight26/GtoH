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
		</table>
	</form>
</body>
</html>