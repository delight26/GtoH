<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@  taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/boardList.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script>
	$(function() {

	});
</script>

<title>승부결과 게시판</title>
</head>
</head>
<body>

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

					<c:forEach var="agrro" items="${aggroList }">
						<tr>
							<td data-hide="phone,tablet" data-class="text-center"
								class="aa tc">${agrro.frbNo }</td>
							<td data-toggle="true"
								class="col-lg-6 col-md-6 col-sm-6 col-xs-6 tl"><a
								href="aggrocontent?frbNo=${agrro.frbNo }&frbHit=${agrro.frbHit }">${agrro.frbTitle }</a></td>
							<td data-hide="phone" data-class="text-center" class="tc">${agrro.frbWriter }</td>
							<td class="bb tc"><fmt:formatDate
									value="${agrro.frbWriteDate }" pattern="yy-MM-dd" /></td>
							<td data-hide="phone,tablet" data-class="text-right"
								data-type="numeric" class="aa tc">${agrro.frbHit }</td>
						</tr>
					</c:forEach>

				</table>

				<c:if test="${ startPage > PAGE_GROUP }">
					<ul class="pager">
						<li><a href="productlist?pageNum=${ startPage - PAGE_GROUP }">[이전]</a></li>
					</ul>
				</c:if>
				<div class="text-center">
					<ul class="pagination">
						<c:forEach var="i" begin="${ startPage }" end="${ endPage }">
							<c:if test="${ i == currentPage }">
								<li class="disabled"><a href="#">${ i }</a></li>
							</c:if>
							<c:if test="${ i != currentPage }">
								<li><a href="productlist?pageNum=${ i }">${ i }</a></li>
							</c:if>
						</c:forEach>
					</ul>
				</div>
				<c:if test="${ endPage < pageCount }">
					<ul class="pager">
						<li><a href="productlist?pageNum=${ startPage + PAGE_GROUP }">[다음]</a></li>
					</ul>
				</c:if>
				<button type="button" class="btn btn-info"
					onclick="window.location.href='aggrowrite'">글쓰기</button>
			</div>
		</div>
	</div>

























</body>
</html>