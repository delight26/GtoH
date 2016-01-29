<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
<div>
	<a href="getNoticeList?pageNum=${ pageNum }">목록으로</a>
</div>