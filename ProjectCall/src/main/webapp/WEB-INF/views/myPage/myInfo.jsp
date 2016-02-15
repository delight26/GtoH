<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<script type="text/javascript">
$(function() {
	 $("#btnDropMember").on(
	            "click",
	            function() {
	               if (confirm('탈퇴하셔도 이전의 기록들을 지우지 않습니다.\n해당 기능이 필요하면 관리자에게 문의해 주십시요.\n정말 탈퇴하시겠습니까? ')) {
	                  $(location).attr(
	                        'href',
	                        "deleteMember");
	               }
	            });
});

function passCheck(){
		  $.ajax({
		        url: "jbPassCheck",
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
}
</script>
<body>
 <!-- myInfo 모달 -->
            <div class="modal-header" style="font-size: 25px; background: #E4E3F3; color: #7092BE; border-radius: 8px;">
               <button type="button" class="close" data-dismiss="modal">&times;</button>
               <span><b>MY INFO</b></span>
            </div>
            <div class="modal-body">
            <table>
            	<tr>
            		<td rowspan="6">
            			<c:if test="${ loginUser.profilPhoto != null }">
            			<img src="resources/uploadimages/${ loginUser.profilPhoto }" width="220px" height="220px" style="border-radius: 110px;"/>
            			</c:if>
            			<c:if test="${ loginUser.profilPhoto == null }">
            			<img src="resources/images/member/mypage_defalut.png" width="200px"/>
            			</c:if>
            		</td>
            		<td class="td1">Email</td>
            		<td>${ loginUser.email }</td>
            	</tr>
            	<tr>
            		<td class="td1">Name</td>
            		<td>${ loginUser.name }</td>
            	</tr>
            	<tr>
            		<td class="td1">Nickname</td>
            		<td>${ loginUser.nickName }</td>
            	</tr>
            	<tr>
            		<td class="td1">Gender</td>
            		<td>${ loginUser.gender }</td>
            	</tr>
            	<tr>
            		<td class="td1">Phone</td>
            		<td>${ loginUser.phone }</td>
            	</tr>
            	<tr>
            		<td class="td1">Say</td>
            		<td>${ loginUser.word }</td>
            	</tr>
            </table>
            </div>
            <div class="modal-footer">
            	<input type="button" id="btnDropMember" class="btn btn-warning btn-block-sm" value="회원탈퇴" />
            	<button id="btnUpdateMemberInfo" class="btn btn-info btn-block-sm" onclick="passCheck()">정보수정</button>
            </div>
</body>
