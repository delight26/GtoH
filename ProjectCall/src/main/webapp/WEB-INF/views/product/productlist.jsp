<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="productbuy">
		<table>
			<c:forEach var="p" items="${pList }">
				<tr>
					<td><a href="productcontent?pNo=${p.pNo }"><img src="resources/uploadimages/${p.pImage }" /></a></td>
				</tr>
				<tr>
					<td>${p.pName }</td>
				</tr>
				<tr>
					<td>${p.pPrice }</td>
				</tr>
			</c:forEach>
		</table>
	</form>
</body>
</html>