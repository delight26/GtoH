<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<script>
   $(function() {
      $("#btnDropMember").on(
            "click",
            function() {

               if (confirm('탈퇴하시겠습니까?')) {
                  $(location).attr(
                        'href',
                        "deleteMember?loginUser="
                              + $("#loginUser").val());
               }

            });

      $("#myInfo").on("click", function(){
    	  $.ajax({
  	        url: "jbmyInfo",
  	        type:"POST",
  	        dataType: "text",
  	        success: function(responseData, statusText, xhr){
  	        	var result = responseData;
  	        	$('#myModal').modal({
  	        		remote : $('.modal-content').html(result)
  	        		});
  	        },
  	        error : function(xhr, statusText, responseData){
  	           alert("error : " + statusText + "." + xhr.status+ "/ " + xhr.responseText);
  	        }
  	     });
      });
      $('#myMatch').on("click", function(){
    	  $.ajax({
    	        url: "jbmyMatch",
    	        type:"POST",
    	        dataType: "text",
    	        success: function(responseData, statusText, xhr){
    	        	var result = responseData;
    	        	$('#myModal').modal({
    	        		remote : $('.modal-content').html(result)
    	        		});
    	        },
    	        error : function(xhr, statusText, responseData){
    	           alert("error : " + statusText + "." + xhr.status+ "/ " + xhr.responseText);
    	        }
    	     });
      });
      $('#myPoint').on("click", function(){
    	  $.ajax({
    	        url: "jbmyPoint",
    	        type:"GET",
    	        dataType: "text",
    	        success: function(responseData, statusText, xhr){
    	        	var result = responseData;
    	        	$('#myModal').modal({
    	        		remote : $('.modal-content').html(result)
    	        		});
    	        },
    	        error : function(xhr, statusText, responseData){
    	           alert("error : " + statusText + "." + xhr.status+ "/ " + xhr.responseText);
    	        }
    	     });
      });
      $('#myModal').on('hide', function(){
    		$('.modal-content').empty();
    	});
   });
   
</script>
<!-- 결과등록 로직 변경 16.02.14 - 기존작업과의 충돌을 피하기위해 새로운 디비 테이블 작성 (fightresult)  -->
<script>
	function fightresultmyself(result, fightNumber){
		var url = "fightresultmyself?fightNumber=" + fightNumber
		+ "&result=" + result;
		var temp = Number(result);
		var real = false;
		if(temp == 0){
			real = confirm("정말로 패배 하셨습니까?");
		}else {
			real = confirm("정말로 승리 하셨습니까?");
		}
		if(real){
/* 			location.href = "fightresultmyself?fightNumber=" + fightNumber
					+ "&result=" + result;
 */		
 			var form = document.createElement("form");
 			form.setAttribute("method","POST");
 			form.setAttribute("action", url);
 			form.setAttribute("fightNumber", fightNumber);
 			form.setAttribute("result", result);
 			document.body.appendChild(form);
 			form.submit();
		}		
	}
</script>
<style>
.td1 {
	font-family: 'consolas';
	font-weight: bold;
	text-align: center;
	width: 100px;
	margin: 0 10px;
}
#matchModal {
	width: 580px;
	text-align: center;
}
#matchModal th {
	text-align: center;	
}
#matchModal td {
	font-size: 13px;
}
</style>

<map name="Map_sys" id="Map_sys">
	<area shape="circle" coords="753,100,60" id="myInfo">
	<area shape="circle" coords="695,240,60" id="myMatch">
	<area shape="circle" coords="753,380,60" id="myPoint">
</map>

<input type="hidden" value="${ loginUser.email }" id="loginUser" />
	<div style="border-bottom: 4px solid #7092BE; margin: 30px 0"><span style="font-size: 48px"><b>MY PAGE</b></span> <span>나의 대결 현황과 개인정보를 수정할 수 있습니다.</span></div>
	 <div style="position:absolute">  
        <div style="position:relative;left:230px;top:123px;">
        	<c:if test="${ member.profilPhoto != null }">
        		<img src="resources/uploadimages/${ member.profilPhoto }" width="240px" height="240px" style="border-radius: 115px;"/>
        	</c:if>
        	<c:if test="${ member.profilPhoto == null }">
        		<img src="resources/images/member/mypage_defalut.png" width="240px"/>
        	</c:if>
        </div>  
    </div>  
    <img src="resources/images/mypage_img.png" width="980px" usemap="#Map_sys"/>
    
<!--    <button id="btnUpdateMemberInfo" data-toggle="modal" -->
<!--       data-target="#passwordCheck">회원정보 수정</button> -->
   <input type="button" id="btnDropMember" value="회원탈퇴" />
   