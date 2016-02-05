<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.project.call.domain.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html >
<html>
<head>
<meta content="charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">

</style>
<script type="text/javascript">
	$(function() {
		$("#listBox").hide();
		$("#search").on("keyup", function(e) {
					var nickName = $("#search").val();

					$.ajax({
								type : "POST",
								url : "nickNameSearch",
								data : {nickName: nickName},
								success : function(data, textStatus, xhr) {
									$("#listBox").show().css("border",
											"1px solid #a8a8a8").css(
											"border-top", "1px solid #1EB501")
											.css("z-index", "9999");

									$("#resultList").html(data);
									$("#resultList li").hover(
											function() {
												$(this).addClass("hover").css(
														"cursor", "pointer");
											}, function() {
												$(this).removeClass("hover");
											});
								},
								error : function(xhr, textStatus) {
								}
							});
				}).on("blur", function(e) {
			setTimeout(function() {
				$("#listBox").hide();
			}, 150);
		}).on("focus", function(e) {
			$("#listBox").show();
		});

		$("#resultList").on("click", "li.searchList", function(e) {
			$("#search").val($(this).text());
			$("#listBox").hide();
		});

	});
</script>
</head>
<body>
	<div>
		<input type="search" name="search" id="search" placeholder="닉네임 검색">
		<div id="listBox">
			<ul id="resultList"></ul>
		</div>
	</div>
	<table>
		<tr>
			<th>사진</th>
			<th>랭킹</th>
			<th>닉네임</th>
			<th>레벨</th>
			<th>전적</th>
			<th>승률</th>
			<th>지역</th>
		</tr>
		<c:forEach items="${rankingList }" var="l">
			<c:choose>
				<c:when test="${l.name =='관리자' }">
				</c:when>
				<c:otherwise>
					<tr>
						<th><img src="http://placehold.it/70x70"></th>
						<th>${l.rank }</th>
						<th><c:choose>
								<c:when test="${loginUser.nickName.equals(l.nickName) }">
				${l.nickName }
				</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${loginUser.email == null }">
				${l.nickName }
				</c:when>
										<c:otherwise>
											<a
												href="YSAddNoteForm?email=${l.email }&nickName=${l.nickName}"
												onClick="window.open(this.href, '', 'width=400, height=430'); return false;">${l.nickName }</a>
										</c:otherwise>
									</c:choose>
								</c:otherwise>
							</c:choose></th>
						<th>${l.level }</th>
						<th>${l.win }승${ l.lose}패</th>

						<th><c:choose>
								<c:when test="${l.win == 0 }">0%
								</c:when>
								<c:otherwise>
									<fmt:formatNumber value="${(l.win/(l.lose+l.win) * 100) }"
										pattern="0.0" />%</th>
				</c:otherwise>
			</c:choose>
			<th>${l.area }</th>
			</tr>
			</c:otherwise>
			</c:choose>
		</c:forEach>
	</table>
</body>
</html>