<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.project.call.domain.*" %>
<% 
Member m = (Member)session.getAttribute("loginUser"); 
%>
<!DOCTYPE html >
<html>
<head>
<meta content="charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="resources/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/aggrowrite.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/agrrowrite.css">
</head>
<body>

<script>
/*  $(function() {
	 $("#").on("click", function() {
	
	if($("#title").val () != "") {
		
		$ajax({
			url : 'title',
			type : 'post',
			datatype : "text",
			data : ({
				
				loginUser: $("#loginUser").val(),
				title : $("#title").val()
				
			}),
			success : function(result, status, xhr){
				if(result == 1){
					$("FreeBoardWrite").submit();
				}else {
					alert('제목이나 내용을 입력해주세요');
				}	
			}
		
		});
 */		 

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
	<div class="modal-content">
		<form action="FreeBoardWrite" enctype="multipart/form-data" method="post" class="form-horizontal"
			 onsubmit="return edit1()">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">자유게시판 글쓰기</h4>
			</div>

			<div class="modal-body">
				<div class="form-group">
                    <label for="writer" class="col-lg-2 control-label">글쓴이</label>
                    <div class="col-lg-10">
                        <input type="text"	class="form-control" name="writer" id="writer"
						value="${ loginUser.nickName }" readonly /> <input type="hidden"
						name="email" value="${ loginUser.email }" /> <input type="hidden"
						name="area" value="aggro" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="title" class="col-lg-2 control-label">제목</label>
                    <div class="col-lg-10">
                        <input type="text" class="form-control"
						name="title" id="title" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="content" class="col-lg-2 control-label">내용</label>
                    <div class="col-lg-10">
                        <textarea class="form-control" name="content" id="content" rows="8"></textarea>
                    </div>
                </div>
				<div class="form-group">
					<label for="image" class="col-lg-2 control-label">사진</label>
					<div class="col-lg-10">
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