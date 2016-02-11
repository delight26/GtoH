<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
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
<script>
function aggrodelete(frbNo){
	conf = confirm("정말 삭제하시겠습니까?");
	if(conf){
		document.location.href="aggrodelete?frbNo=" + frbNo;
	}
	
}

function search(){
	var search = $('#search').val();
	document.location.href = "aggrosearch?search=" + search;
}
</script>
<form>
<div class="container-fluid">
	<div class="row">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
<a href="aggrowrite" data-toggle="modal" data-target="#myModal" style="float: right;"><img src="resources/images/btn_write.gif" width="70px" style="border-radius: 4px; margin: 5px 15px"/></a><br/>
	<table id="table2" class="table table-striped table-hover footable">
	<thead>
	<c:if test="${fn:length(aggroList)==0 }">
	검색된 글이 없습니다.
	</c:if>
	<c:if test="${fn:length(aggroList)!=0 }">
		<tr>
			<th data-hide="phone,tablet" data-class="text-center" class="aa tc">번호</th>
			<th data-toggle="true" class="col-lg-6 col-md-6 col-sm-6 col-xs-6 tc">제목</th>
			<th data-hide="phone" data-class="text-center" class="tc">글쓴이</th>
			<th data-hide="phone,tablet" data-class="text-center" data-sort-ignore="true" class="bb tc">작성일</th>
			<th data-hide="phone,tablet" data-class="text-right" data-type="numeric" class="aa tc">조회수</th>
   			<th></th>
		</tr>
	</thead>
		<c:forEach var="agrro" items="${aggroList }" >
		<tr>
		<td class="aa tc">${agrro.frbNo }</td>
		<td data-toggle="true"><a href="aggrocontent?frbNo=${agrro.frbNo }&frbHit=${agrro.frbHit }&pageNum=${currentPage }">${agrro.frbTitle }&nbsp;<span style="font-size: 12px">(${agrro.frbComment })</span></a></td>
		<td class="tc">${agrro.frbWriter }</td>
		<td class="bb tc" >${agrro.frbWriteDate }&nbsp;&nbsp;</td>
		<td class="aa tc">${agrro.frbHit }</td>
		<td>
				<c:set var="frbEmail" value="${agrro.frbEmail}"/>
				<c:set var="nickName" value="admin" />
				<c:if test="${loginUser.email == frbEmail || loginUser.nickName == nickName}">
       			 <a href="agrroupdate?frbNo=${agrro.frbNo }" data-toggle="modal" data-target="#myModal">수정</a>
       			 <a href="javascript:aggrodelete(${agrro.frbNo })">삭제</a>
   				 </c:if>
				</td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="6" class="listPage">
				<c:if test="${ startPage > PAGE_GROUP }">
					<a href="agrroboard?pageNum=${ startPage - PAGE_GROUP }">[이전]</a>
				</c:if>			
				<c:forEach var="i" begin="${ startPage }" end="${ endPage }">
				<c:if test="${ i == currentPage }">
					[${ i }]
				</c:if>			
				<c:if test="${ i != currentPage }">
					<a href="agrroboard?pageNum=${ i }">[${ i }]</a>
				</c:if>			
				</c:forEach>
				<c:if test="${ endPage < pageCount }">
					<a href="agrroboard?pageNum=${ startPage + PAGE_GROUP }">[다음]</a>
				</c:if>		
			</td>	
		</tr>
		</c:if>
	</table>
	<div style="float:right">
		<input type="text" id="search" name="search" />
		<a href="javascript:search()">검색</a>
	</div>
</div>
</div>
</div>
</form>