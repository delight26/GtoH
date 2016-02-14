<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script type="text/javascript" src="resources/js/jquery-1.11.3.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script>
function checkpassword(){
    if ($("#password").val() != "") {
	
       $.ajax({
          url : 'passwordCheck',
          type : 'post',
          datatype : "Number",
          data : ({

             'loginUser' : $("#loginUser").val(),
             'password' : $("#password").val()

          }),
          success : function(result, status, xhr) {
        	  if (result == 1) {
        		  updateMember();
             } else {
                alert('비밀번호를 확인해주세요');
             }

          },
          error : function(xhr, statusText, error) {
             alert('에러 : ' + statusText + ", " + xhr.status);
          }
       });

    } else {
       alert('비밀번호를 입력해주세요.');
    }

}
function updateMember(){
	$.ajax({
        url : 'updateMemberInfoForm',
        type : 'post',
        datatype : "text",
        data : ({

           'loginUser' : $("#loginUser").val(),
        }),
        success : function(responseData, status, xhr) {
        	var result = responseData;
        	$('#myPageModal').modal({
        		remote : $('.modal-content').html(result)
        		});
        },
        error : function(xhr, statusText, error) {
           alert('에러 : ' + statusText + ", " + xhr.status);
        }
     });

}
</script>
<body>
            <div class="modal-header" style="border-radius: 8px;">
               <button type="button" class="close" data-dismiss="modal">&times;</button>
               <h4 class="modal-title" style="text-align: center;">비밀번호 확인</h4>
               <p style="visibility: hidden;">1</p>
                  <div class="form-group">
                     <div class="col-sm-12 col-sm-12">
                        <input type="password" class="form-control"  id="password"
                            name="passwordch" placeholder="password"> <input
                           type="hidden" name="loginUser" id="loginUser"
                           value="${ loginUser.email }" />
                     </div>
                  </div>
                  <div class="form-group">
                     <div class="col-sm-12 col-sm-12">
                        <button type="button" id="btnpasswordCheck"
                           class="btn btn-info btn-block" onclick="checkpassword()">확인</button>
                     </div>
                  </div>
            </div>
</body>
