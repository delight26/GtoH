<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script>
function noticeDelete(no) {
	var conf = confirm("이 게시글을 삭제하시겠습니까?");
	
	if(conf) {
		location.href="noticeDelete?no=" + no;
	}
}
</script>
<input type="hidden" name="pageNum" value="${ pageNum }" />

<table>
	<tr>
		<th>${ notice.frbNo }</th>
		<th>${ notice.frbTitle }</th>
		<th>${ notice.frbWriter }</th>
	</tr>
	<tr>
		<td colspan="3" class="postTime">Posted at <fmt:formatDate value="${ notice.frbWriteDate }" 
			pattern="yyyy-MM-dd HH:mm:ss"/></td>
	</tr>
	<tr>
	<c:if test="${ notice.photo1 != null }">
		<td colspan="3"><img src="resources/images/photo1/${ notice.photo1 }" width="300px" height="300px" /></td>
	</c:if>
		<td>${ notice.frbContent }</td>
	</tr>
</table>
<div class="board_btn">
	<a href="getNoticeList?pageNum=${ pageNum }"><img src="resources/images/board_btn_list.gif"/></a>&nbsp;
	<c:if test="${ sessionScope.loginUser.nickName == 'admin' }">
	<img src="resources/images/board_btn_delete.gif" onclick="noticeDelete('${ notice.frbNo }')"/>
	</c:if>
</div>
<div>
	
	
</div>