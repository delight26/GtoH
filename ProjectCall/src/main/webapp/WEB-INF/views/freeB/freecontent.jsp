<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/aggrocontent.js"></script>
<script type="text/javascript">
function aggrodelete(frbNo){
	conf = confirm("정말 삭제하시겠습니까?");
	if(conf){
		document.location.href="freedelete?frbNo=" + frbNo;
	}
	
}
</script>
</head>
<body>
	<div>
		<a href="freepre?frbNo=${frb.frbNo }&pageNum=${pageNum }">이전글</a> 
		<a href="freenext?frbNo=${frb.frbNo }&pageNum=${pageNum }">다음글</a>
	</div>
	<div>
		<a href="FreeBoardList?pageNum=${pageNum }">목록</a>
		<input type="hidden" id="frbNo" value="${frb.frbNo }" />
	</div>
	<div>
		<table>
			<tr>
				<td>${frb.frbTitle }</td>
				<td>${frb.frbWriteDate }</td>
				<td>
				<c:set var="frbEmail" value="${frb.frbEmail }"/>
				<c:set var="nickName" value="admin" />
				<c:if test="${loginUser.email == frbEmail || loginUser.nickName == nickName}">
       			 <a href="freeupdate?frbNo=${frb.frbNo }" data-toggle="modal" data-target="#myModal">수정</a>
       			 <a href="javascript:freedelete(${frb.frbNo })">삭제</a>
   				 </c:if>
				</td>
			</tr>
			<tr>
				<td>${frb.frbWriter }</td>
			</tr>
			<tr>
				<td>${frb.frbContent }</td>
			</tr>
			<tr>
			<td colspan="2"><img src="resources/uploadimages/${frb.photo1 }"  style="width:500px"/></td>
			</tr>
			<tr>
				<td>댓글 ${frb.frbComment }</td>
				<td>조회수 ${frb.frbHit }</td>
			</tr>
			
		</table>
		<div id="comment">
		
		</div>
		<table>
			<tr>
				<td><textarea id="contentwrite" cols="50" rows="3"></textarea></td>
				<td><input type="button" id="btnwrite" value="작성하기" onclick="commentwrite('${loginUser.email }')" /></td>
			</tr>
		</table>
	</div>
</body>
</html>