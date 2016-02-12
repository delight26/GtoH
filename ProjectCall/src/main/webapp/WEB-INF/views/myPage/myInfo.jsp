<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script>
   $(function() {

      $("#btnpasswordCheck").on("click", function() {

         if ($("#password").val() != "") {

            $.ajax({
               url : 'passwordCheck',
               type : 'post',
               datatype : "number",
               data : ({

                  loginUser : $("#loginUser").val(),
                  password : $("#password").val()

               }),
               success : function(result, status, xhr) {
                  if (result == 1) {
                     $("#passwordCheckForm").submit();
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

      });

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

      $("#btnAddFightResultForm").on(
            "click",
            function() {
               $(location).attr(
                     'href',
                     "addFightResultBoardForm?fightNumber="
                           + $("#fightNumber").val());
            });

   });
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
<input type="hidden" value="${ loginUser.email }" id="loginUser"/>
	
</body>
</html>