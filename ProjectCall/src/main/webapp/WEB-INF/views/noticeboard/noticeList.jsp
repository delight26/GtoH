<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/board.css" rel="stylesheet" />
<table class="t_board">
	<tr class="tr_board">
		<th>번호</th>
		<th>제목</th>
		<th>글쓴이</th>
		<th>날짜</th>
		<th>조회</th>
	</tr>
	
	<c:if test="${ listCount > 0 }" >
	<c:forEach var="notice" items="${ noticeList }" varStatus="s">
	<tr class="tr_board">
		<td class="no">${ notice.frbNo }</td>
		<c:if test="${ notice.frbTitle.length() > 20 }">
			<td class="title"><a href="getNoticeContent?no=${ notice.frbNo }&pageNum=${ currentPage }">
				${ notice.frbTitle.substring(0, 21) }...
				<c:if test="${s.count <= 5}"><img src="resources/images/board_new.gif"/></c:if>
				</a></td>
		</c:if>
		<c:if test="${ notice.frbTitle.length() <= 20 }">
			<td class="title"><a href="getNoticeContent?no=${ notice.frbNo }&pageNum=${ currentPage }">
				${ notice.frbTitle }
				<c:if test="${s.count <= 5}"><img src="resources/images/board_new.gif"/></c:if>
				</a></td>
		</c:if>
		<td class="writer"><b>${ notice.frbWriter }</b></td>
		<td class="date"><fmt:formatDate value="${ notice.frbWriteDate }" 
			pattern="yyyy-MM-dd"/></td>
		<td class="count">${ notice.frbHit }</td>
		<td></td>
	</tr>
	</c:forEach>
	<tr class="t_nav">
		<td colspan="2">
		<c:if test="${ startPage > PAGE_GROUP }">
				<a href="getNoticeList?pageNum=${ startPage - PAGE_GROUP }">[이전]</a>
		</c:if>
		<c:forEach var="i" begin="${ startPage }" end="${ endPage }">
				<c:if test="${ i == currentPage }">
					[${ i }]
				</c:if>			
				<c:if test="${ i != currentPage }">
					<a href="getNoticeList?pageNum=${ i }">[${ i }]</a>
				</c:if>			
		</c:forEach>
		<c:if test="${ endPage < pageCount }">
				<a href="getNoticeList?pageNum=${ startPage + PAGE_GROUP }">[다음]</a>
		</c:if>	
		</td>
		<td colspan="3">
		</td>
	</tr>
	</c:if>
</table>

<div class="board_btn">
	<a href="getNoticeList"><img src="resources/images/board_btn_list.gif"/></a>&nbsp;
	<c:if test="${ loginUser.nickName == 'admin' }">
	<a href="noticeWriteForm"><img src="resources/images/board_btn_write.gif"/></a>
	</c:if>
</div>