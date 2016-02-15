function findid(){
	var name=$('#name').val();
	var birthday = $('#birthday').val();
	$.ajax({
        url: "getMemberId",
        type:"post",
        data: {"name" : name, "birthday" : birthday},
        dataType: "text",
        success: function(responseData, statusText, xhr){
        	var result = responseData;
       	 $('#getId').html(result);
        },
        error : function(xhr, statusText, responseData){
           alert("error : " + statusText + "." + xhr.status+ "/ " + xhr.responseText);
        },
     });
}


function find(){
     var id = $('#id option:selected').val();
     alert(id);
      var data = "id=" + id;
          createXhr();
          xhr.onreadystatechange = callbackFind; 
          xhr.open("POST", "findIdPassAjax", true);
          xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
          xhr.send(data); 
  }
  function callbackFind(){
      if(xhr.readyState==4){      
          if(xhr.status == 200){  
              var resTxt = xhr.responseText;  
              document.getElementById("findCheckLayer").innerHTML = resTxt;     
          }else{
              alert("요청 처리가 정상적으로 되지 않았습니다.\n"+xhr.status);
          }
      }
  }
 