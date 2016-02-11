<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/board.css" rel="stylesheet" />
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/writeTable.css" rel="stylesheet" />
<div>
	<form name="noticeWriteForm" action="noticeWrite" enctype="multipart/form-data" method="post">
	
	<input type="hidden" name="no" value="${ no }" />
	<input type="hidden" name="pageNum" value="${ pageNum }" />
	<input type="hidden" name="email" value="${ sessionScope.loginUser.email }" />
		
		<table class="t_write">
			<tr>
				<th>*작성자</th>
				<td style="font-size: 10px;"><input type="text" name="writer" value="${ sessionScope.loginUser.name }" readonly/></td>
			</tr>
			<tr>
				<th>*제목</th>
				<td class="td_title"><input type="text" name="title" required/></td>
			</tr>
			<tr>
				<th>*내용</th>
				<td class="td_content"><textarea name="content" required></textarea></td>
			</tr>
			<tr>
				<th>사진첨부</th>
				<td><input type="file" name="photo1"/></td>
			</tr>
		</table>
		<div class="board_btn">
			<input type="image" src="resources/images/btn_ok.gif" onclick="document.noticeWriteForm.submit()" width="50px" style="border-radius: 4px;"/>&nbsp;
			<a href="javascript:history.back()"><img src="resources/images/board_btn_back.gif" width="70px" style="border-radius: 4px;"/></a>
		</div>
	</form>
</div>
