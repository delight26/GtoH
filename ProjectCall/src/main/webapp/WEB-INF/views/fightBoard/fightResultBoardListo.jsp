<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script>
$(function() {
	

});
</script>
<style>
td, th {
	border: 1px solid black;
}
</style>
<title>승부결과 게시판</title>
</head>
<body>
	<form id="addFightResultBoardForm" action="addFightResultBoardResult"
		enctype="multipart/form-data" method="post">
		
		<table>
			
			<!-- 	관리자 로그인이 아닌 경우 isAdminCheck가 1인 게시물만 -->
			<c:if test="${ loginUser.email != 'admin@ghcall.com' }">
				<tr>
					<th>글번호</th>
					<th>제목</th>
					<th>글쓴이</th>
					<th>게시일</th>
					<th>조회수</th>
				</tr>
				<c:forEach var="frb" items="${ fightResultBoardList }">
					<c:if test="${ frb.isAdminCheck == 1 }">
						
						<tr>
							<td>
								${ frb.no }
							</td>
							<td>
								<a href="fightResultBoardContent?no=${ frb.no }">
									${ frb.title }</a>
							</td>
							<td>
								${ frb.writer }
							</td>
							<td>
								${ frb.writeDate }
							</td>
							<td>
								${ frb.hit }
							</td>
						</tr>
					</c:if>
				</c:forEach>
			</c:if>
			
			<!--	관리자 로그인일 경우 isAdminCheck가 x인 걸 관리자가 확인할 수 있도록 한다 -->
			<c:if test="${ loginUser.email == 'admin@ghcall.com' }">
				<tr>
					<th>글번호</th>
					<th>제목</th>
					<th>글쓴이</th>
					<th>게시일</th>
					<th>조회수</th>
					<th>결과승인</th>
				</tr>
				<c:forEach var="frb" items="${ fightResultBoardList }">
					
					<tr>
						<td>
							${ frb.no }
						</td>
						<td>
							<a href="fightResultBoardContent?no=${ frb.no }">
								${ frb.title }</a>
						</td>
						<td>
							${ frb.writer }
						</td>
						<td>
							${ frb.writeDate }
						</td>
						<td>
							${ frb.hit }
						</td>
						<td>
							<c:if test="${ frb.isAdminCheck == 0 }">
								승인대기
							</c:if>
							<c:if test="${ frb.isAdminCheck ==1 }">
								승인완료
							</c:if>
						</td>
						
					</tr>
				</c:forEach>
			</c:if>
			
		</table>
		<input type="hidden" id="loginUser" value="${ loginUser.email }" />
		
	</form>
	
	
	
	
	
	
	
	
	
</body>
</html>














