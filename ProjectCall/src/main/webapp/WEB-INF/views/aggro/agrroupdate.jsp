<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도발 수정</title>
<script>
$(document).on('change', '.btn-file :file', function() {
	  var input = $(this);
	      numFiles = input.get(0).files ? input.get(0).files.length : 1;
	      label = input.val();
	      $("#filePath").val(label);
	});

	$(document).ready( function() {
	    $('.btn-file :file').on('fileselect', function(event, numFiles, label) {
	        
	        var input = $(this).parents('.input-group').find(':text'),
	            log = numFiles > 1 ? numFiles + ' files selected' : label;
	        
	        if( input.length ) {
	            input.val(log);
	        } else {
	            if( log ) alert(log);
	        }
	        
	    });
	});
</script>
<style>
#image_preview {
    display:none;
}
.btn-file {
  position: relative;
  overflow: hidden;
}
.btn-file input[type=file] {
  position: absolute;
  top: 0;
  right: 0;
  min-width: 100%;
  min-height: 100%;
  font-size: 100px;
  text-align: right;
  filter: alpha(opacity=0);
  opacity: 0;
  background: red;
  cursor: inherit;
  display: block;
}
input[readonly] {
  background-color: white !important;
  cursor: text !important;
}
</style>
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/aggrowrite.js"></script>
</head>
<body>

<div class="modal-content">
	<form action="aggroupdateresult" enctype="multipart/form-data" method="post" class="form-horizontal">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">&times;</button>
			<h4 class="modal-title">도발게시판 글 수정</h4>
		</div>

		<div class="modal-body">
			<div class="form-group">
                   <label for="writer" class="col-lg-2 control-label">글쓴이</label>
                   <div class="col-lg-10">
                       <input type="text"
					class="form-control" name="writer" id="writer"
					value="${frb.frbWriter }" readonly /> <input type="hidden" name="email" value="${loginUser.email }" />
			<input type="hidden" name="area" value="aggro" />
			<input type="hidden" name="frbNo" value="${frb.frbNo }" />
                   </div>
               </div>
               <div class="form-group">
                   <label for="title" class="col-lg-2 control-label">제목</label>
                   <div class="col-lg-10">
                       <input type="text" class="form-control"
					name="title" id="title" value="${frb.frbTitle }" />
                   </div>
               </div>
               <div class="form-group">
                   <label for="content" class="col-lg-2 control-label">내용</label>
                   <div class="col-lg-10">
                       <textarea class="form-control" name="content" id="content" rows="8">${frb.frbContent }</textarea>
                   </div>
               </div>
			<div class="form-group">
				<label for="image" class="col-lg-2 control-label">사진</label>
				<div class="col-lg-10">
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
			<div class="input-group">
				<input type="submit" value="글쓰기" class="btn btn-info" />
				<input type="button" class="btn btn-default" data-dismiss="modal" value="취소" />
			</div>
		</div>
		
	</form>
</div>



	<div id="image_preview">
		<img style="width:500px" src="#" />
		<br />
		<a href="#">사진 지우기</a>
	</div>