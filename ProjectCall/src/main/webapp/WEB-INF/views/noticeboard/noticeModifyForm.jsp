<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/board.css" rel="stylesheet" />
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/writeTable.css" rel="stylesheet" />
<div>
	<form name="noticeModifyForm" action="noticeModify" enctype="multipart/form-data" method="post">
	
	<input type="hidden" name="no" value="${ modify.frbNo }" />
	<input type="hidden" name="pageNum" value="${ pageNum }" />
	<input type="hidden" name="email" value="${ sessionScope.loginUser.email }" />
		
		<table class="t_write">
			<tr>
				<th>*작성자</th>
				<td><input type="text" name="writer" value="${ modify.frbWriter }" readonly/></td>
			</tr>
			<tr>
				<th>*제목</th>
				<td class="td_title"><input type="text" name="title" value="${ modify.frbTitle }" readonly/></td>
			</tr>
			<tr>
				<th>*내용</th>
				<td class="td_content"><textarea name="content" required>${ modify.frbContent }</textarea></td>
			</tr>
			<tr>
				<th>사진첨부</th>
				<td><input type="file" name="photo1" value="${ modify.photo1 }"/></td>
			</tr>
		</table>
		<div class="board_btn">
			<input type="image" src="resources/images/board_btn_ok.gif" onclick="document.noticeWriteForm.submit()"/>&nbsp;
			<a href="javascript:history.back()"><img src="resources/images/board_btn_back.gif"/></a>
		</div>
	</form>
</div>
