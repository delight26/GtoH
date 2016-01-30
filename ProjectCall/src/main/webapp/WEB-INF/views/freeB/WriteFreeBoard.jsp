<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="WriteFreeBoard" enctype="multipart/form-data" >
	<table>
	<tr>작&nbsp;성&nbsp;자
		<td><input type="text" name="email" /></td>
	</tr>
	
	<tr>
	<td>
		<select name="area" placeholder="선택">
			<option value="notice">[공지]</option>
			<option value="freetolk">[잡담]</option>
			<option value="fishing">[떡밥]</option>
			<option value="question">[질문]</option>
			<option value="information">[정보]</option>
		</select></td>
	<td>제&nbsp;목	
		<input type="text" name="title" placeholder="제목을&nbsp;입력해주세요" /></td>
		
	<tr>	
	<td>비&nbsp;밀&nbsp;번&nbsp;호	
		<input type="password" name="pass" placeholder="비밀번호를&nbsp;입력해주세요" /></td>	
	</tr>
	
	<tr>	
		<td><textarea name="content" rows="10" cols="60"  placeholder="내용을&nbsp;입력해주세요" /></td>
	</tr>
	
	<tr>
		<td><input type="file" name="photo" value="파일 추가"/></td>
	</tr>
		
	</table>
</form>
</body>
</html>