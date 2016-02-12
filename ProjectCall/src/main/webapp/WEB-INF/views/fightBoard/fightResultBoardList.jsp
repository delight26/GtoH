<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@  taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<script>
	$(function() {

	});
</script>
<style>
@media ( max-width : 500px) {
	.bb {
		display: none;
	}
}

@media ( max-width : 799px) {
	.aa {
		display: none;
	}
}

@media only screen and (min-width: 800px) and (max-width: 1023px) {
	.aa {
		
	}
}

@media only screen and (min-width: 1024px) {
	.aa {
		
	}
}
.tc {
	text-align: center;
}
</style>
<a href="">결과쓰기</a>
	<form id="addFightResultBoardForm" action="addFightResultBoardResult"
		enctype="multipart/form-data" method="post">
		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<table id="table2" class="table table-striped table-hover footable">
						<thead>
							<!-- 	관리자 로그인이 아닌 경우 isAdminCheck가 1인 게시물만 -->
							<c:if test="${ loginUser.email != 'admin@ghcall.com' }">
								<tr>
									<th data-hide="phone,tablet" data-class="text-center"
										class="aa tc">번호</th>
									<th data-toggle="true"
										class="col-lg-6 col-md-6 col-sm-6 col-xs-6 tc">제목</th>
									<th data-hide="phone" data-class="text-center" class="tc">글쓴이</th>
									<th data-hide="phone,tablet" data-class="text-center"
										data-sort-ignore="true" class="bb tc">게시일</th>
									<th data-hide="phone,tablet" data-class="text-right"
										data-type="numeric" class="aa tc">조회수</th>
								</tr>
							</c:if>
							<!--	관리자 로그인일 경우 isAdminCheck가 x인 걸 관리자가 확인할 수 있도록 한다 -->
							<c:if test="${ loginUser.email == 'admin@ghcall.com' }">
								<tr>
									<th data-hide="phone,tablet" data-class="text-center"
										class="aa tc" >번호</th>
									<th data-toggle="true"
										class="col-lg-6 col-md-6 col-sm-6 col-xs-6 tc">제목</th>
									<th data-hide="phone" data-class="text-center" class="tc">글쓴이</th>
									<th data-hide="phone,tablet" data-class="text-center"
										data-sort-ignore="true" class="bb tc" >게시일</th>
									<th data-hide="phone,tablet" data-class="text-right"
										data-type="numeric" class="aa tc">조회수</th>
								</tr>
							</c:if>
						</thead>
						<tbody>
							<!-- 	관리자 로그인이 아닌 경우 isAdminCheck가 1인 게시물만 -->
							<c:if test="${ loginUser.email != 'admin@ghcall.com' }">
								<c:forEach var="frb" items="${ fightResultBoardList }">
									<c:if test="${ frb.isAdminCheck == 1 }">
										<tr>
											<td class="aa tc">${ frb.no }</td>
											<td data-toggle="true"><a
												href="fightResultBoardContent?no=${ frb.no }"> ${ frb.title }</a>
											</td>
											<td class="tc">${ frb.writer }</td>
											<td class="bb tc" ><fmt:formatDate
													value="${ frb.writeDate }" pattern="yy-MM-dd" /></td>
											<td class="aa tc">${ frb.hit }</td>
										</tr>
									</c:if>
								</c:forEach>
							</c:if>

							<!--	관리자 로그인일 경우 isAdminCheck가 x인 걸 관리자가 확인할 수 있도록 한다 -->
							<c:if test="${ loginUser.email == 'admin@ghcall.com' }">
								<c:forEach var="frb" items="${ fightResultBoardList }">

									<tr>
										<td class="aa tc">${ frb.no }</td>
										<td data-toggle="true"><a
											href="fightResultBoardContent?no=${ frb.no }"> ${ frb.title }</a>
										</td>
										<td class="tc">${ frb.writer }</td>
										<td class="bb tc" ><fmt:formatDate
												value="${ frb.writeDate }" pattern="yy-MM-dd" /></td>
										<td class="bb tc" >${ frb.hit }</td>
										<td class="tc"><c:if test="${ frb.isAdminCheck == 0 }">
								승인대기
							</c:if> <c:if test="${ frb.isAdminCheck == 1 }">
								승인완료
							</c:if></td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>
			
					<c:forEach begin="1" step="1" end="${fightResultBoardList.get(0).getPageSize() }" var="i">
				     <c:choose>
                     <c:when test="${pageNum == i }">[${i }]</c:when>
                     <c:otherwise>
                        <a href="fightResultBoardList?pageNum=${i }">[${i }]</a>
                     </c:otherwise></c:choose>
					
					
					</c:forEach>
				</div>
			</div>
		</div>
		<input type="hidden" id="loginUser" value="${ loginUser.email }" />
	</form>