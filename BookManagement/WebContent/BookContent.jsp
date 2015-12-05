<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.bookmanage.member.*, com.bookmanage.dao.*"%>
<%@ page import="java.util.*" %>
<%
Book book = (Book)request.getAttribute("book");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function deleteProduct (BookNumber){
	var conf = confirm("정말 삭제 하시겠습니까?");
	if(conf){
		location.href="bookdelete.do?BookNumber="+BookNumber;
	}
}
</script>
</head>
<body>
	<div>
		<h1>상품 상세 정보</h1>
		<table>
			<tr>
				<th><%=book.getBookName() %></th>
			<tr>
			<tr>
				<td class="image">
					<%
						if (book.getPhoto() == null) {
					%> 이미지<br />파일 없음 <%
						} else {
					%> <img
					src="<%=book.getPhoto() %>" />
				</td>
			</tr>
			<%
				}
			%>
			<tr>
				<td class="info">
				작가: <%=book.getAuthor() %><br/>
				출판사 : <%=book.getBookCompany()%><br />
				판매가 : <%=book.getPrice()%><br /> 
				도서 번호 : <%=book.getBookNumber()%><br />
					
				</td>
			</tr>
		</table>
		<p>
			<a href="booklist.do">상품 리스트 보기</a>
		</p>
	</div>
</body>
</html>