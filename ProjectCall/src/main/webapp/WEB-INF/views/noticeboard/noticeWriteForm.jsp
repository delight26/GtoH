<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="resources/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/aggrowrite.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/agrrowrite.css">
</head>
<script>
function edit1(){
	var title = $("#title").val();
	var content = $("#content").val();
	var file = $("#file").val();
	
	 if(title == ""){
		alert("제목을 입력하세요");
		return false;
	} else if(content == ""){
		alert("내용을 입력하세요");
		return false;
	}  	
}
</script>
<body>
	<div class="modal-content">
	<form name="noticeWriteForm" action="noticeWrite" enctype="multipart/form-data" method="post" 
		class="form-horizontal" onsubmit="return edit1()">
	<input type="hidden" name="no" value="${ no }" />
	<input type="hidden" name="pageNum" value="${ pageNum }" />
	<input type="hidden" name="email" value="${ sessionScope.loginUser.email }" />
	<div class="modal-header" style="background: #E4E3F3; color: #7092BE; text-align: center; border-radius: 4px">
		<button type="button" class="close" data-dismiss="modal">&times;</button>
		<h2 class="modal-title"><b>자유게시판 글쓰기</b></h2>
	</div>
	<div class="modal-body">
		<div class="form-group">
			<label for="writer" class="col-lg-2 control-label">작성자</label>
            <div class="col-lg-9">
				<input type="text"	class="form-control" name="writer" id="writer"
					value="관리자" readonly />
			</div>
		</div>
		<div class="form-group">
			<label for="title" class="col-lg-2 control-label">제목</label>
			<div class="col-lg-9">
            	<input type="text" class="form-control" name="title" id="title" />
            </div>
		</div>
		<div class="form-group">
			<label for="content" class="col-lg-2 control-label">내용</label>
            <div class="col-lg-9">
            	<textarea class="form-control" name="content" id="content" rows="8"></textarea>
            </div>
		</div>
		<div class="form-group">
			<label for="image" class="col-lg-2 control-label">사진</label>
			<div class="col-lg-9">
				<div class="input-group">
					<span class="input-group-btn"> <span
						class="btn btn-primary btn-file"> 찾아보기<input
							type="file" name="photo1" id="image" accept="image/*" />
					</span>
					</span> <input type="text" id="filePath" class="form-control" readonly></input>
				</div>
			</div>
		</div>
	</div>
	<div class="modal-footer">
		<div class="input-group" id="btns" style="margin: 0 auto;">
			<input type="submit" value="글쓰기" class="btn btn-info" />
			<input type="button" class="btn btn-default" data-dismiss="modal" value="취소" />
		</div>
	</div>
	<div class="modal-footer" id="image_preview">
		<img style="width: 500px" src="#" /> <br /> <a id="removePhoto" href="#">사진 지우기</a>
	</div>
	</form>
</div>
</body>
</html>