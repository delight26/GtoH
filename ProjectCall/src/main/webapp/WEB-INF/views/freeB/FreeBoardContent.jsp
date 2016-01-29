<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table>
	<tr>
		<td>글 번호</td>
		<td>${frb.frbNo }</td>
		<td>조회 수</td>
		<td>${frb.frbHit }</td>
		<td>작성 일</td>				
		<td>${frb.frbWriteDate }</td>		
	</tr>
	
	<tr>
		<td>작성자</td>
		<td>${frb.frbWriter }</td>		
		<td>비밀번호</td>
		<td>${frb.frbPass }</td>
		<td>내용</td>
		<td>${frb.frbContent }</td>	
	</tr>
	
	<input type="button" name="Modify" value="수정"
	onclick="window.location.href='FreeBoardModify'" />
	<input type="button" name="Delete" value="삭제"
	onclick="window.location.href='FreeBoardDelet'" />
	<input type="button" name="Cancle" value="취소"
	onclick="window.location.href='FreeBoardList'" />
</table>
</body>
</html>