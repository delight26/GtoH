<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.project.call.domain.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style>
#total { width: 980px; margin-top: 10px; }
#total th {
	height: 30px;
	text-align: center;
	line-height: 30px;
	background: #489CFF;
	border-bottom: 3px solid #BDBDBD;
	color: #fff;
}
#total td {
	text-align: center;
	height: 50px;
	line-height: 50px;
	border-bottom: 1px solid #EAEAEA;
}
#total th:nth-child(2n+2) { width: 250px; }
#total th:first-child, #total th:last-child, #total th:nth-child(5n+3) { width: 90px; }
#total th:first-child { border-radius: 5px 0 0 0; }
#total th:last-child { border-radius: 0 5px 0 0; }
#total td:first-child { font-size: 23px; font-weight: bold }
#total td:nth-child(8n+2) { text-align: right; }
#total td:nth-child(8n+3) { width: 200px; }
a:hover { text-decoration: none; }
</style>
<img src="resources/images/rank.gif" width="700px;"/>
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
						<td>
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
												onClick="window.open(this.href, '', 'width=400, height=430'); return false;">&nbsp;<img src="resources/images/note.jpg" width="15px"/></a>
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