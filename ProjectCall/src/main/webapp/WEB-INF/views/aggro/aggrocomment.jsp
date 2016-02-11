<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
     
     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/aggrocomment.js"></script>
</head>
<body>
<table>
	<c:forEach var="c" items="${cList }" >
		<tr>
			<td id=comment_id>${c.cWriter }</td>
			<td id=comment_date>${c.writeDate }</td>
			<c:set var="email" value="${c.cEmail }" />
			<c:if test="${loginUser.email == email }">
			<td id="modify">
			<a href="javascript:commentupdate(${c.cNo }) ">수정</a></td>
			<td><a href="javascript:commentdelete(${c.cNo }, ${c.bNo })">삭제</a></td>
			</c:if>
		</tr>
		<tr>
		<td id="comment_content">${c.cContent }
		<input type="hidden" id="content${c.cNo }" value="${c.cContent }" />
		</td>
		</tr>
		<tr>
		<td class="modif_content" id="modify${c.cNo }" colspan=4><a href="javascript:modifycancel(${c.cNo })">수정 취소</a><br/>
			<textarea id="modifycomment${c.cNo }" rows="" cols="35"></textarea>
			<input type="button" value="확인" onclick="updatecomment(${c.cNo},${c.bNo })"/>
		</td>
		</tr>
		</c:forEach>
		
		
		
</table>
</body>
</html>