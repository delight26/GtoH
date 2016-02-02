<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<a href="aggropre?frbNo=${frb.frbNo }">이전글</a> <a
			href="aggronext?frbNo=${frb.frbNo }">다음글</a>
	</div>
	<div>
		<a>목록</a>
	</div>
	<div>
		<table>
			<tr>
				<td>${frb.frbTitle }</td>
				<td>${frb.frbWriteDate }</td>
			</tr>
			<tr>
				<td>${frb.frbWriter }</td>
			</tr>
			<tr>
				<td>${frb.frbContent }</td>
			</tr>
			<tr>
				<td>댓글 ${frb.frbComment }</td>
				<td>조회수 ${frb.frbHit }</td>
			</tr>
		</table>
	</div>
</body>
</html>