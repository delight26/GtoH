<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도발 글쓰기</title>
<style>
#image_preview {
    display:none;
}
</style>
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="resources/js/aggrowrite.js"></script>
</head>
<body>
<form action="aggrowriteresult" enctype="multipart/form-data" method="post">
	<table>
		<tr>
			<th>글쓴이</th>
			<td>
			<input type="text" name="writer" value="${loginUser.nickName }"/>
			<input type="hidden" name="email" value="${loginUser.email }" />
			<input type="hidden" name="area" value="aggro" />
			</td>
			<td>비밀번호</td>
			<td><input type="password" name="pass" /></td>
		</tr>
		<tr>
			<th>글 제목</th>
			<td><input type="text" name="title" /></td>
		</tr>
		<tr>
			<th>내용</th>
			<td colspan="2">
			<textarea cols="80" rows="20" name="content"></textarea>
			</td>
		</tr>
		<tr>
		<td><input type="submit" value="글쓰기" /></td>
		</tr>
	</table>
    <p>
        <label for="image">사진</label>
        <br />
        <input type="file" name="image" id="image" />
    </p>
	</form>
	<div id="image_preview">
		<img style="width:500px" src="#" />
		<br />
		<a href="#">사진 지우기</a>
	</div>
</body>
</html>