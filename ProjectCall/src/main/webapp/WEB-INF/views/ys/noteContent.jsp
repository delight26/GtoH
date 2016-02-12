<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html >
<html>
<head>
<meta content="charset=UTF-8">
<style>
#note td {padding:2px}
#note {border: 5px solid #BCE55C;}
body {
	background:#BCE55C;
	font-size: 12px;
	font-family:'맑은 고딕'
}
input, textarea {
	background:#F6FFCC;
	font-size: 12px;
	font-family:'맑은 고딕'
}
#note_btn { float: center;margin: 20px 0 0 130px}
</style>
</head>
<body>
	<table id="note">
		<tr><td colspan="4" style="font-size: 14px"><b>쪽지 읽기</b></td></tr>
		<tr><td style="width: 100px">보낸이</td><td><input type="text" value="${note.getNbNickName()}" style="width: 120px" readonly/></td>
			<td style="width: 70px">&nbsp;작성일</td><td><input type="text" value="${note.getNbDate()}" style="width:63px;" readonly/></td>
		</tr>
		<tr><td>제목</td><td colspan="3"><input type="text" value="${note.getNbTitle()}" style="width:300px;" readonly/></td></tr>
		<tr>
			<td>내용</td><td colspan="3"><input type="text" value="${note.getNbContent()}" style="width:300px; height:150px;" readonly/></td>
		</tr>
	</table>
	<div id="note_btn">
	<input type="button" value="답장"
		onclick="window.location.href='YSReplyNoteForm?email=${note.getNbEmail()}&nickName=${note.getNbNickName()}'" />
	<input type="button" value="삭제"
		onclick="window.location.href='YSdeleteNote?nbNo=${note.getNbNo()}&toid=${note.getNbToid()}'" />
	<input type="button" value="목록"
		onclick="javascript:history.back();" />
	</div>
</body>
</html>