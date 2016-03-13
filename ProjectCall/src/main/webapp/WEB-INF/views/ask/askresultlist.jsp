<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<style>
#askTable th {
	font-size: 15px;
}
#askTable td {
	font-size: 12px;
}
</style>
<div style="border-bottom: 3px solid #7092BE; margin: 30px 0"><span style="font-size: 25px"><b>신청한 대결</b></span></div>
<table id="askTable" class="table table-striped table-hover footable">
	<c:if test="${fn:length(aList)==0 }">
	신청한 대결이 없습니다.
	</c:if>
	<c:if test="${fn:length(aList)>0 }">
	<thead>
	<tr>
		<th>대결 도전자/Level</th>
		<th>대결 수락자/Level</th>
		<th>작성일</th>
		<th>대결일</th>
		<th>장소</th>
		<th>메시지</th>
		<th>대결 수정</th>
		<th>대결 취소</th>
		<th>상태</th>
	</tr>
	</thead>
		<c:forEach var="a" items="${aList }" >
	<tr>
		<td>${a.abNickName } / ${a.abEmailRank }&nbsp;&nbsp;</td>
		<td>${a.abToid } / ${a.abToidRank }&nbsp;&nbsp;</td>
		<td>${fn:substring(a.abWriteDate, 0,10) }&nbsp;&nbsp;</td>
		<td>${fn:substring(a.abFightDate, 0,10) }&nbsp;&nbsp;</td>
		<td>${a.abPlace }</td>
		<td>${a.abTell }</td>
		<td><input type="button" value="수정" onclick="askupdate(${a.abNo})" class="btn btn-xs btn-success"/></td>
		<td><input type="button" value="취소" onclick="askdelete(${a.abNo})" class="btn btn-xs btn-danger"/></td>
		<td>
			<c:choose>
				<c:when test="${a.abApproval== 1 }">
		 		대결을 수락하셨습니다.
		 		</c:when>
		 		<c:when test="${a.abApproval== 2 }">
		 		대결을 거절하셨습니다.
		 		</c:when>
		 		<c:otherwise>
		 		수락 대기중입니다.
		 		</c:otherwise>
			</c:choose>
		</td>
	</tr>
		</c:forEach>
	</c:if>
</table>
