<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function aggrodelete(frbNo){
	conf = confirm("정말 삭제하시겠습니까?");
	if(conf){
		document.location.href="aggrodelete?frbNo=" + frbNo;
	}
	
}
</script>
</head>
<body>
	<div>
		<a href="aggropre?frbNo=${frb.frbNo }&pageNum=${pageNum }">이전글</a> 
		<a href="aggronext?frbNo=${frb.frbNo }&pageNum=${pageNum }">다음글</a>
	</div>
	<div>
		<a href="agrroboard?pageNum=${pageNum }">목록</a>
		<input type="hidden" id="frbNo" value="${frb.frbNo }" />
	</div>
	<div>
		<table>
			<tr>
				<td>${frb.frbTitle }</td>
				<td>${frb.frbWriteDate }</td>
				<td>
				<c:set var="frbEmail" value="${frb.frbEmail }"/>
				<c:if test="${loginUser.email == frbEmail }">
       			 <a href="agrroupdate?frbNo=${frb.frbNo }" data-toggle="modal" data-target="#myModal">수정</a>
       			 <a href="javascript:aggrodelete(${frb.frbNo })">삭제</a>
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
		<table>
		<tr>
		<td id=comment_id></td>
		<td id=comment_date></td>
		<td id=comment_content></td>
		</tr>
		<tr>
		<td><textarea id="content" cols="50" rows="3"></textarea></td>
		<td><input type="button" id="btnwrite" value="작성하기" onclick="commentwrite()" /></td>
		<td></td>
		</tr>
		</table>
	</div>
</body>
</html>