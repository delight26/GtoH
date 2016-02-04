<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.project.call.domain.*" %>
<% 
Member m = (Member)session.getAttribute("loginUser"); 

%>
<!DOCTYPE>
<html>
<head>
<meta>
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="resources/js/jquery-1.11.3.min.js"></script>
</head>
<body>
<script>
function modify1(){
	var title = $("#title").val();
	var content = $("#content").val();
	var area = $("#area").val();
	var file = $("#file").val();

	 if(title == ""){
		alert("제목 입력");
		return false;
	} else if(content == ""){
		alert("내용 입력");
		return false;
	} else if(area == "choice"){
		alert("area 선택");
		return false;
	} else if(file == ""){
		alert("파일 선택");
		return false;
	} 
	
	
	
}
</script>

<form action="FreeBoardModify" method="POST" 
		enctype="multipart/form-data" onsubmit="return modify1()">
	
	<table>
	
	<tr>
	<input type="hidden" name="no" value="${ frb.frbNo}">
	</tr>
	
	<tr>
		<td>작&nbsp;성&nbsp;자
		<input type="text" name="email" value="${ frb.frbEmail}" /></td>
	</tr>
	
	<tr>
	<td>지역
		<select name="area" id="area" value="${frb.frbArea }">
			<option value="choice">선택</option>
			<option value="s">서울</option>
			<option value="c">충남/충북</option>
			<option value="j">전라/전북</option>
		</select></td>
		
	</tr>
	
	<tr>	
	<td>제&nbsp;목	
		<input type="text" name="title" id="title" value="${ frb.frbTitle}" /></td>
	</tr>
		
	<tr>	
		<td>내용<textarea name="content" id="content" rows="10" cols="60" >${frb.frbContent }</textarea></td>
	</tr>
	
	<tr>
		<td><input type="file" name="photo" id="file" />	
	</tr>


	</table>
	
	<input type="submit" value="등록" />
	<input type="reset" value="취소" /> 
	
</form>
</body>
</html>