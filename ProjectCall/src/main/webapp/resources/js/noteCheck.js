
$(function(){
	
	 $.ajax({
         type: 'post'
       , url: 'noteCheck'
       ,datatype:'number'
       , data : {toid : $("#nickname").val()}
       , success: function(data) {
       	$("#noteCheck").html("새로운 쪽지 : "+data);
         }});

});



