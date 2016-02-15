<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<body>
<form>
<c:if test="${fn:length(mList)==0 }">
검색된 아이디가 없습니다.
</c:if>
<c:if test="${fn:length(mList)!=0 }">
<select name="id" id="id">
	<option value="">아이디 선택</option>
	<c:forEach var="m" items="${mList }" >
	<option value="${m.email }">${m.email }</option>
	</c:forEach>
	</select>
	<input type="button" onclick="find();" value="인증메일 보내기">
</c:if>
	
</form>
</body>
