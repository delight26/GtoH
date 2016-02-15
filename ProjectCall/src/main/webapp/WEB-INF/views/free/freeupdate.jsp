<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도발 수정</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/freewrite.css">
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/freewrite.js"></script>
</head>
<body>

<div class="modal-content">
	<form action="freeupdateresult" enctype="multipart/form-data" method="post" class="form-horizontal">
			<div class="modal-header" style="background: #E4E3F3; color: #7092BE; text-align: center; border-radius: 4px">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h2 class="modal-title"><b>자유게시판 글수정</b></h2>
			</div>		<div class="modal-body">
			<div class="form-group">
                   <label for="writer" class="col-lg-2 control-label">글쓴이</label>
                   <div class="col-lg-9">
                       <input type="text"
					class="form-control" name="writer" id="writer"
					value="${frb.frbWriter }" readonly /> <input type="hidden" name="email" value="${loginUser.email }" />
			<input type="hidden" name="area" value="free" />
			<input type="hidden" name="frbNo" value="${frb.frbNo }" />
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
								type="file" name="image" id="image" accept="image/*" />
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

