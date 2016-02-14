<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@  taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/boardList.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<c:if test="${ loginUser.nickName == 'admin' }">
		<a href="noticeWriteForm" style="float: right;"><img
			src="resources/images/btn_write.gif" width="70px"
			style="border-radius: 4px; margin: 5px 15px" /></a>
	</c:if>

	<div class="container-fluid">

		<div class="row">

			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">

				<table id="table2" class="table table-striped table-hover footable">

					<tr>
						<th data-hide="phone,tablet" data-class="text-center"
							class="aa tc">번호</th>
						<th data-toggle="true"
							class="col-lg-6 col-md-6 col-sm-6 col-xs-6 tc">제목</th>
						<th data-hide="phone" data-class="text-center" class="tc">글쓴이</th>
						<th data-hide="phone,tablet" data-class="text-center"
							data-sort-ignore="true" class="bb tc">작성일</th>
						<th data-hide="phone,tablet" data-class="text-right"
							data-type="numeric" class="aa tc">조회수</th>
					</tr>

					<c:if test="${ listCount > 0 }">
						<c:forEach var="notice" items="${ noticeList }" varStatus="s">
							<tr class="tr_board">
								<td class="no aa tc">${ notice.frbNo }</td>
								<c:if test="${ notice.frbTitle.length() > 20 }">
									<td class="title"><a
										href="getNoticeContent?no=${ notice.frbNo }&pageNum=${ currentPage }">
											${ notice.frbTitle.substring(0, 21) }... <c:if
												test="${s.count <= 5}">
												<img src="resources/images/board_new.gif" />
											</c:if>
									</a></td>
								</c:if>
								<c:if test="${ notice.frbTitle.length() <= 20 }">
									<td class="title"><a
										href="getNoticeContent?no=${ notice.frbNo }&pageNum=${ currentPage }">
											${ notice.frbTitle } <c:if test="${s.count <= 5}">
												<img src="resources/images/board_new.gif" />
											</c:if>
									</a></td>
								</c:if>
								<td class="writer tc"><b>${ notice.frbWriter }</b></td>
								<td class="date bb tc"><fmt:formatDate
										value="${ notice.frbWriteDate }" pattern="yyyy-MM-dd" /></td>
								<td class="count aa tc">${ notice.frbHit }</td>
							</tr>
						</c:forEach>



					</c:if>

				</table>

				<c:if test="${ startPage > PAGE_GROUP }">
					<ul class="pager">
						<li><a
							href="getNoticeList?pageNum=${ startPage - PAGE_GROUP }">[이전]</a></li>
					</ul>
				</c:if>
				<div class="text-center">
					<ul class="pagination">
						<c:forEach var="i" begin="${ startPage }" end="${ endPage }">
							<c:if test="${ i == currentPage }">
								<li class="disabled"><a href="#">${ i }</a></li>
							</c:if>
							<c:if test="${ i != currentPage }">
								<li><a href="getNoticeList?pageNum=${ i }">${ i }</a></li>
							</c:if>
						</c:forEach>
					</ul>
				</div>
				<c:if test="${ endPage < pageCount }">
					<ul class="pager">
						<li><a
							href="getNoticeList?pageNum=${ startPage + PAGE_GROUP }">[다음]</a></li>

					</ul>
				</c:if>

				

			</div>
		</div>
	</div>
