
$(function() {
	$.ajax({
		url : 'ListAllComment',
		type : 'post',
		datatype : "text",
		data : ({
			bno: $("#bno").val()
		}),
		success : function(result, status, xhr) {
			$("#commentList").append(result);
		},
		error : function(xhr, statusText, error) {
			alert('에러 : ' + statusText + ", " + xhr.status);
		}
	});
	
	$("#btnSubmit").on("click", function() {
		if ($("#comment").val() != "") {
			$.ajax({
				url : 'AddComment',
				type : 'post',
				datatype : "text",
				data : ({
					bno: $("#bno").val(),
					comment: $("#comment").val(),
					loginUser: $("#loginUser").val()
				}),
				success : function(result, status, xhr) {
					$("#commentList").empty();
					$("#commentList").append(result);
					$("#comment").val("");
				},
				error : function(xhr, statusText, error) {
					alert('에러 : ' + statusText + ", " + xhr.status);
				}
			});
		} else {
			alert('댓글 입력');
		}
	});
	
	$("#btnModify").on("click", function() {
		if($("#writer").val() != $("#loginUser").val()){
		}else{
			$.ajax({
				url : 'modifyComment',
				type : 'post',
				datatype : "text",
				data : ({
					bno: $("#bno").val(),
					comment: $("#comment").val(),
					loginUser: $("#loginUser").val()
				}),
				success : function(result, status, xhr) {
					$("#commentList").empty();
					$("#commentList").append(result);
					$("#comment").val("");
				},
				error : function(xhr, statusText, error) {
					alert('에러 : ' + statusText + ", " + xhr.status);
				}
			});
		}
	});
	
});
function btnCommentModify(fno) {
	
	if($("#writer").val() != $("#loginUser").val()){
	}else{
		var content = $('#replayComment'+fno).val();
		$("#modifycomment"+cNo).val(content);
		$("#modify"+cNo).show();
	}
};
