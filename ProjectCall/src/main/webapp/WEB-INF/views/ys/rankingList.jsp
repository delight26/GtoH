<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.project.call.domain.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style>
* { margin: 0 auto; }
#total { width: 980px; margin-top: 10px; border-collapse: collapse;}
#total th {
	height: 30px;
	text-align: center;
	line-height: 30px;
	background: #489CFF;
	border-bottom: 3px solid #BDBDBD;
	color: #fff;
	border-collapse: collapse;
}
#total td {
	text-align: center;
	height: 50px;
	line-height: 50px;
	border-bottom: 1px solid #EAEAEA;
	border-collapse: collapse;
}
#total th:nth-child(2n+2) { width: 250px; }
#total th:first-child, #total th:last-child, #total th:nth-child(5n+3) { width: 110px; }
#total th:first-child { border-radius: 5px 0 0 0; }
#total th:last-child { border-radius: 0 5px 0 0; }
#total td:first-child { font-size: 23px; font-weight: bold }
#total td:nth-child(8n+2) { text-align: right; }
#total td:nth-child(8n+3) { width: 200px; }
a:hover { text-decoration: none; }
a:visited { text-decoration: none; }
#resultList { list-style: none; }
#idSearch { float: right; margin: 0 220px 10px 0;}
#rankImg { margin-left: 200px;}
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
<div id="rankImg"><img src="resources/images/rank.gif" width="700px;"/></div>
<div id="idSearch">
	<input type="search" name="search" id="search" placeholder="닉네임 검색">
	<div id="listBox">
		<ul id="resultList"></ul>
	</div>
	</div>
	<table id="total">
		<tr>
			<th>RANK</th>
			<th colspan="2">NICKNAME</th>
			<th>LEVEL</th>
			<th>MATCH RECORD</th>
			<th>SCORE</th>
			<th>AREA</th>
		</tr>
		<c:forEach items="${rankingList }" var="l">
			<c:choose>
				<c:when test="${l.name =='관리자' }">
				</c:when>
				<c:otherwise>
					<tr>
						<td>
						<c:if test="${ l.rank == 1 }"><img src="${pageContext.request.contextPath}/resources/images/gold.jpg" width="40px" height="50px"/></c:if>
						<c:if test="${ l.rank == 2 }"><img src="${pageContext.request.contextPath}/resources/images/silver.jpg" width="40px" height="50px"/></c:if>
						<c:if test="${ l.rank == 3 }"><img src="${pageContext.request.contextPath}/resources/images/bronze.jpg" width="40px" height="50px"/></c:if>
						<c:if test="${ l.rank >= 4 }"><i>${ l.rank }</i></c:if>
						</td>
						<td>
						<c:if test="${ l.profilPhoto == null }">
							<img src="${pageContext.request.contextPath}/resources/images/member/profile_default.png" width="25px" height="25px"/></c:if>
						<c:if test="${ l.profilPhoto != null }">
							<img src="${pageContext.request.contextPath}/resources/images/member/${ l.profilPhoto }" width="25px" height="25px"/></c:if>
						</td>
						<td style="text-align: left; padding-left: 18px">
						<c:choose>
								<c:when test="${loginUser.nickName.equals(l.nickName) }">
									${l.nickName }
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${loginUser.email == null }">
										${l.nickName }
										</c:when>
										<c:otherwise>
										${l.nickName }
											<a href="YSAddNoteForm?email=${l.email }&nickName=${l.nickName}"
												onClick="window.open(this.href, '쪽지보내기', 'width=424, height=268, toolbar=no, menubar=no, scrollbars=no, location=no, resizable=no'); return false;">&nbsp;<img src="resources/images/note.jpg" width="15px"/></a>
										</c:otherwise>
									</c:choose>
								</c:otherwise>
							</c:choose></td>
						<td>${l.level }</td>
						<td>${l.win }승 &nbsp;${ l.lose}패</td>

						<td><c:choose>
								<c:when test="${l.win == 0 }">0%
								</c:when>
								<c:otherwise>
									<fmt:formatNumber value="${(l.win/(l.lose+l.win) * 100) }"
										pattern="0.0" />%</td>
				</c:otherwise>
			</c:choose>
			<td>${l.area }</td>
			</tr>
			</c:otherwise>
			</c:choose>
		</c:forEach>
	</table>