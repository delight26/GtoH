<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<table>
<tr>
<td><a href="javascript:newAsk()">대결신청</a></td>
</tr>
	<c:if test="${fn:length(aList)==0 }">
	신청받은 대결이 없습니다.
	</c:if>
	<c:if test="${fn:length(aList)>0 }">
	<tr>
		<th>대결 도전자/포인트</th>
		<th>대결 수락자/포인트</th>
		<th>작성일</th>
		<th>대결일</th>
		<th>장소</th>
		<th>메시지</th>
		<th>승낙</th>
		<th>대결 수정</th>
		<th>대결 취소</th>
	</tr>
		<c:forEach var="a" items="${aList }" >
	<tr>
		<td>${loginUser.nickName } / ${loginUser.point }&nbsp;&nbsp;</td>
		<td>${a.abToid } / ${a.abToidRank }&nbsp;&nbsp;</td>
		<td>${fn:substring(a.abWriteDate, 0,10) }&nbsp;&nbsp;</td>
		<td>${fn:substring(a.abFightDate, 0,10) }&nbsp;&nbsp;</td>
		<td>${a.abPlace }</td>
		<td>${a.abTell }</td>
		<td>
			<c:choose>
				<c:when test="${a.abApproval== 0 }">
				<input type="button" value="수정하기" onclick="askupdate(${a.abNo})" />
				</c:when>
				<c:when test="${a.abApproval== 1 }">
		 		수락완료
		 		</c:when>
		 		<c:when test="${a.abApproval== 2 }">
		 		대결거절
		 		</c:when>
			</c:choose>
		</td>
		<td><input type="button" value="취소하기" onclick="askdelete(${a.abNo})" /></td>
	</tr>
		</c:forEach>
	</c:if>
</table>
