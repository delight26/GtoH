$( document ).ready(function() {
      var frbNo = $("#frbNo").val();
      commentList(1);
   });

function commentList(pageNum){
	  var frbNo = $("#frbNo").val();
$.ajax({
   url: "aggrocomment",
   type:"get",
   data: {"frbNo" : frbNo, "pageNum" : pageNum },
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

function commentwrite(email){
	var content = $('#contentwrite').val();
	if(email == null){
		alert("로그인 하셔야 댓글작성이 가능합니다");
		History.back();
	}
	var frbNo = $('#frbNo').val();
	$.ajax({
        url: "aggrocommentwrite",
        type:"post",
        data: {"frbNo" : frbNo, "content" : content, "email" : email},
        dataType: "text",
        success: function(responseData, statusText, xhr){
        	var result = responseData;
        	 $('#comment').html(result);
        	 $('#contentwrite').val('');
        },
        error : function(xhr, statusText, responseData){
           alert("error : " + statusText + "." + xhr.status+ "/ " + xhr.responseText);
        }
     });
	
}