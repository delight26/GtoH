<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.project.call.domain.*, java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@  taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
//   	String email = (String)session.getAttribute("email");
	Member member = (Member) session.getAttribute("loginUser");
	String email = member.getEmail();

   boolean loginUser = false;
	if (email == null) {
		loginUser = false;
	} else {
		loginUser = true;
	}  
%>
<script src="resources/js/jquery-1.11.3.min.js"></script>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script type="text/javascript">
 	 function writeForm() {
		 if (<%=loginUser%>) {
			 window.location.href="writeForm"
		} else {
			alert("로그인하세요");
			window.location.href="loginform"
		}
	}  
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
<a style="float: right;"><img src="resources/images/btn_write.gif" width="70px" style="border-radius: 4px; margin: 5px 15px" onclick="writeForm()"/></a>
<div class="container-fluid">
	<div class="row">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	<table id="table2" class="table table-striped table-hover footable">
		<thead>
		<tr>
			<th data-hide="phone,tablet" data-class="text-center"
										class="aa tc">번호</th>
			<th data-toggle="true"
										class="col-lg-6 col-md-6 col-sm-6 col-xs-6 tc">제목</th>
			<th data-hide="phone" data-class="text-center" class="tc">작성자</th>
			<th data-hide="phone,tablet" data-class="text-center"
										data-sort-ignore="true" class="bb tc">등록일</th>
			<th data-hide="phone,tablet" data-class="text-right"
										data-type="numeric" class="aa tc">조회 수</th>
		</tr>
		</thead>
		<c:forEach var="frb" items="${frbList }">
			<tr>
				<td class="aa tc">${frb.frbNo }</td>
				<td data-toggle="true"><a href="FreeBoardContent?frbNo=${frb.frbNo }">
						${frb.frbTitle }</a></td>
				<td class="tc">${frb.frbWriter }</td>
				<td class="bb tc" ><fmt:formatDate value="${frb.frbWriteDate }" pattern="yyyy-MM-dd" /></td>
				<td class="aa tc">${frb.frbHit}</td>
			</tr>
		</c:forEach>
		
		<tr>
			<td colspan="6" class="listPage">
				<c:if test="${ startPage > PAGE_GROUP }">
					<a href="FreeBoardList?pageNum=${ startPage - PAGE_GROUP }">[이전]</a>
				</c:if>			
				<c:forEach var="i" begin="${ startPage }" end="${ endPage }">
				<c:if test="${ i == currentPage }">
					[${ i }]
				</c:if>			
				<c:if test="${ i != currentPage }">
					<a href="FreeBoardList?pageNum=${ i }">[${ i }]</a>
				</c:if>			
				</c:forEach>
				<c:if test="${ endPage < pageCount }">
					<a href="FreeBoardList?pageNum=${ startPage + PAGE_GROUP }">[다음]</a>
				</c:if>		
			</td>	
		</tr>
		
	<tr>
	<td><input type="button" name="write" value="글쓰기" 
			onclick="window.location.href='writeForm'" /></td>
	<td><input type="button" name="AllList" value="목록보기"
			onclick="window.location.href='FreeBoardList'" /></td>
	
	</tr>
	</table>
</div>
</div>
</div>
