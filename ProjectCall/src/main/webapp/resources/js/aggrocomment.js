$( document ).ready(function() {
	$(".modif_content").hide();
});
function commentupdate(cNo){
	var content = $('#content'+cNo).val();
	$("#modifycomment"+cNo).val(content);
	$("#modify"+cNo).show();
	
}

function modifycancel(cNo){
	$("#modify"+cNo).hide();
}

function updatecomment(cNo, bNo){
	var content = $('#modifycomment'+cNo).val();
	$.ajax({
        url: "aggrocommentupdate",
        type:"post",
        data: {"cNo" : cNo, "content" : content, "frbNo" : bNo},
        dataType: "text",
        success: function(responseData, statusText, xhr){
        	var result = responseData;
       	 $('#comment').html(result);
       	 $('#contentwrite').val('');
        },
        error : function(xhr, statusText, responseData){
           alert("error : " + statusText + "." + xhr.status+ "/ " + xhr.responseText);
        },
     });
}

function commentdelete(cNo, bNo){
	conf = confirm('정말 삭제하시겠습니까?');
	if(conf){
		$.ajax({
	        url: "aggrocommentdelete",
	        type:"post",
	        data: {"cNo" : cNo, "frbNo" : bNo},
	        dataType: "text",
	        success: function(responseData, statusText, xhr){
	        	var result = responseData;
	       	 $('#comment').html(result);
	        },
	        error : function(xhr, statusText, responseData){
	           alert("error : " + statusText + "." + xhr.status+ "/ " + xhr.responseText);
	        }
	     });
	}
}