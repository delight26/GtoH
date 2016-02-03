$( document ).ready(function() {

      var frbNo = $("#frbNo").val();
      
      $.ajax({
         url: "aggrocomment",
         type:"get",
         data: {"frbNo" : frbNo },
         dataType: "text",
         success: function(responseData, statusText, xhr){
            var result = responseData;
         },
         error : function(xhr, statusText, responseData){
            alert("error : " + statusText + "." + xhr.status+ "/ " + xhr.responseText);
         }
      });
      
   });