function addmember() {
	location.href="hyunsu/addMember/step1";
}


function findIdPass() {
	$.ajax({
        url: "findIdPass",
        type:"GET",
        dataType: "text",
        success: function(responseData, statusText, xhr){
        	var result = responseData;
        	$('.modal-content').html(result);
        },
        error : function(xhr, statusText, responseData){
           alert("error : " + statusText + "." + xhr.status+ "/ " + xhr.responseText);
        }
     });
	$('#myModal').modal('show');
}