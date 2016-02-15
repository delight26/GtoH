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
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/aggrowrite.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/agrrowrite.css">
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

<div class="modal-content">
	<form action="aggroupdateresult" enctype="multipart/form-data" method="post" class="form-horizontal">
		<div class="modal-header" style="background: #E4E3F3; color: #7092BE; text-align: center; border-radius: 4px">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h2 class="modal-title"><b>자유게시판 글수정</b></h2>
		</div>	
		<div class="modal-body">
		<div class="form-group">
                   <label for="title" class="col-lg-2 control-label">글쓴이</label>
                   <div class="col-lg-9">
                       <input type="text" class="form-control"
					name="writer" id="writer" value="${frb.frbWriter }" readonly/>
                   </div>
               </div>
               <div class="form-group">
                   <label for="title" class="col-lg-2 control-label">제목</label>
                   <div class="col-lg-9">
                       <input type="text" class="form-control"
					name="title" id="title" value="${frb.frbTitle }" />
                   </div>
               </div>
               <div class="form-group">
                   <label for="content" class="col-lg-2 control-label">내용</label>
                   <div class="col-lg-9">
                       <textarea class="form-control" name="content" id="content" rows="8">${frb.frbContent }</textarea>
                   </div>
               </div>
               <div class="form-group">
				<label for="image" class="col-lg-2 control-label">사진</label>
				<div class="col-lg-9">
					<div class="input-group">
						<span class="input-group-btn"> <span
							class="btn btn-primary btn-file"> 찾아보기<input
								type="file" name="photo" id="image" accept="image/*" />
						</span>
						</span> <input type="text" id="filePath" class="form-control" readonly></input>
					</div>
				</div>
			</div>
		</div>
		<div class="modal-footer">
			<div class="input-group" id="btns" style="margin: 0 auto;">
				<input type="submit" value="수정하기" class="btn btn-info" />
				<input type="button" class="btn btn-warning" data-dismiss="modal" value="취소" />
			</div>
		</div>
		<div class="modal-footer" id="image_preview">
			<img style="width: 500px" src="#" /> <br /> <a id="removePhoto"  href="#">사진 지우기</a>
		</div>
	</form>
</div>


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