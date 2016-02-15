<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<link rel="stylesheet" href="resources/css/boardContent.css">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script 	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script 	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script type="text/javascript">
$( document ).ready(function() {
	$.ajax({
        url: "askresultlist",
        type:"GET",
        dataType: "text",
        success: function(responseData, statusText, xhr){
        	var result = responseData;
        		$('#askresult').html(result);
        		askrecevelist();
        },
        error : function(xhr, statusText, responseData){
           alert("error : " + statusText + "." + xhr.status+ "/ " + xhr.responseText);
        }
     });
	function askrecevelist(){
	$.ajax({
        url: "askrecevelist",
        type:"GET",
        dataType: "text",
        success: function(responseData, statusText, xhr){
        	var result = responseData;
        		$('#askreceve').html(result);
        },
        error : function(xhr, statusText, responseData){
           alert("error : " + statusText + "." + xhr.status+ "/ " + xhr.responseText);
        }
     });
	}
});
function newAsk(){
	$('#myModal').modal({
		remote : "newAsk"		
		});
}

function askupdate(abNo){
	document.location.href = "askresultupdate?abNo="+abNo;
}

function askdelete(abNo){
	document.location.href = "askresultdelete?abNo="+abNo;
}
</script>
</head>
<body>
<div id="askresult">

</div><br/><br/>
<div id="askreceve">

</div>
</body>
</html>