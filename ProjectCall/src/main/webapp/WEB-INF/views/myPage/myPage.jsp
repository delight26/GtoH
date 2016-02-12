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

<map name="Map_sys" id="Map_sys">
	<area shape="circle" coords="753,100,60" data-toggle="modal" data-target="#myInfo">
	<area shape="circle" coords="695,240,60" data-toggle="modal" data-target="#myMatch">
	<area shape="circle" coords="753,380,60" data-toggle="modal" data-target="#myPoint">
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
	<h3>${ member.nickName }님의 정보</h3>
	<div id="ser_1">
		<h4>내 랭킹</h4>
		${ member.rank }위 / ${ winningRate }% 
	</div>
	<div id="ser_2">
		<h4>승패(승률)</h4>
		${ member.win }승 / ${ member.lose }패(${ winningRate }%) 
	</div>
	
	<div id="ser_3">
		<h4>내 포인트 정보</h4>
		총 ${ member.point }포인트 획득 후 ${ member.usepoint }포인트 사용 
	</div>
	
	<div>
		   <h4>대결 전적</h4>

      <table>

         <tr>
            <th>대결 신청일</th>
            <th>대결 시행일</th>
            <th>대결 요청자</th>
            <th>대결 수락자</th>
            <th>대결 결과</th>
            <th>결과 등록</th>
         </tr>

         <c:forEach var="f" items="${ fightList }">
            <tr>
               <td><fmt:formatDate value="${ f.fbCallDate }" pattern="yy-MM-dd" /></td>
               <td><fmt:formatDate value="${ f.fbResultDate }" pattern="yy-MM-dd" /></td>
               <td>${ f.fbP1 }</td>
               <td>${ f.fbP2 }</td>
               <td><c:if test="${f.fbresult == 0 }">
                     	결과를 등록해 주세요
                  </c:if> <c:if test="${f.fbresult != 0 }">
                     	승인 대기중
                  </c:if></td>
               <td><c:if test="${f.fbresult == 0 }">
                     <input type="button" value="등록" id="btnAddFightResultForm"
                        name="btnAddFightResultForm" />
                     <input type="hidden" id="fightNumber" value="${ f.fbNo }" />
                  </c:if> <c:if test="${f.fbresult != 0 }">
                    	 등록 완료
                  </c:if></td>
            </tr>
         </c:forEach>

      </table>

      <c:if test="${ startPage > PAGE_GROUP }">
         <ul class="pager">
            <li><a href="myPage?pageNum=${ startPage - PAGE_GROUP }">[이전]</a></li>
         </ul>
      </c:if>
      <div class="text-center">
         <ul class="pagination">
            <c:forEach var="i" begin="${ startPage }" end="${ endPage }">
               <c:if test="${ i == currentPage }">
                  <li class="disabled"><a href="#">${ i }</a></li>
               </c:if>
               <c:if test="${ i == 0 }">
                  <li class="disabled"><a href="#">${ i }</a></li>
               </c:if>
               <c:if test="${ i !=0 && i != currentPage }">
                  <li><a href="myPage?pageNum=${ i }">${ i }</a></li>
               </c:if>
            </c:forEach>
         </ul>
      </div>
      <c:if test="${ endPage < pageCount }">
         <ul class="pager">
            <li><a href="myPage?pageNum=${ startPage + PAGE_GROUP }">[다음]</a></li>
         </ul>
      </c:if>
      
      


   </div>
   <button id="btnUpdateMemberInfo" data-toggle="modal"
      data-target="#passwordCheck">회원정보 수정</button>
   <input type="button" id="btnDropMember" value="회원탈퇴" />
   <input type="hidden" id="loginUser" value='${ loginUser.email }' />

 <!-- myInfo 모달 -->
   <div class="modal fade" id="myInfo" role="dialog" style="border-radius: 8px;">
      <div class="modal-dialog">
         <!-- Modal content-->
         <div class="modal-content">
            <div class="modal-header" style="font-size: 25px; background: #E4E3F3; color: #7092BE; border-radius: 8px;">
               <button type="button" class="close" data-dismiss="modal">&times;</button>
               <span><b>MY INFO</b></span>
            </div>
            <div class="modal-body">
            <table>
            	<tr>
            		<td rowspan="6">
            			<c:if test="${ member.profilPhoto != null }">
            			<img src="resources/uploadimages/${ member.profilPhoto }" width="220px" height="220px" style="border-radius: 110px;"/>
            			</c:if>
            			<c:if test="${ member.profilPhoto == null }">
            			<img src="resources/images/member/mypage_defalut.png" width="200px"/>
            			</c:if>
            		</td>
            		<td class="td1">Email</td>
            		<td>${ loginUser.email }</td>
            	</tr>
            	<tr>
            		<td class="td1">Name</td>
            		<td>${ member.name }</td>
            	</tr>
            	<tr>
            		<td class="td1">Nickname</td>
            		<td>${ member.nickName }</td>
            	</tr>
            	<tr>
            		<td class="td1">Gender</td>
            		<td>${ member.gender }</td>
            	</tr>
            	<tr>
            		<td class="td1">Phone</td>
            		<td>${ member.phone }</td>
            	</tr>
            	<tr>
            		<td class="td1">Say</td>
            		<td>${ member.word }</td>
            	</tr>
            </table>
            </div>
            <div class="modal-footer">
            	<button id="btnUpdateMemberInfo" data-toggle="modal" class="btn btn-info btn-block-sm"
      					data-target="#passwordCheck">정보수정</button>
            </div>
          </div>
        </div>
      </div>
      
 <!-- myMatch 모달 -->
   <div class="modal fade" id="myMatch" role="dialog" style="border-radius: 8px;">
      <div class="modal-dialog">
         <!-- Modal content-->
         <div class="modal-content">
            <div class="modal-header" style="font-size: 25px; background: #E4E3F3; color: #7092BE;border-radius: 8px;">
               <button type="button" class="close" data-dismiss="modal">&times;</button>
               <span><b>MY MATCH</b></span>
            </div>
            <div class="modal-body">
				<div>
					<table id="matchModal">
						<tr>
							<th>신청일</th>
							<th>시행일</th>
							<th>요청자</th>
							<th>수락자</th>
							<th>대결 결과</th>
							<th>결과 등록</th>
						</tr>

						<c:forEach var="f" items="${ fightList }">
            			<tr>
			               <td><fmt:formatDate value="${ f.fbCallDate }" pattern="yy-MM-dd" /></td>
			               <td><fmt:formatDate value="${ f.fbResultDate }" pattern="yy-MM-dd" /></td>
			               <td>${ f.fbP1 }</td>
			               <td>${ f.fbP2 }</td>
			               <td><c:if test="${f.fbresult == 0 }">
                     		결과를 등록해 주세요
                 		 </c:if> <c:if test="${f.fbresult != 0 }">
                     		승인 대기중
                  		 </c:if></td>
               				<td><c:if test="${f.fbresult == 0 }">
                     			<input type="button" value="등록" id="btnAddFightResultForm"
                        				name="btnAddFightResultForm" />
                     			<input type="hidden" id="fightNumber" value="${ f.fbNo }" />
                  		 </c:if> <c:if test="${f.fbresult != 0 }">
                    	 	등록 완료
                  		 </c:if></td>
            			</tr>
        	 		 </c:forEach>

					</table>

					<c:if test="${ startPage > PAGE_GROUP }">
						<ul class="pager">
							<li><a href="myPage?pageNum=${ startPage - PAGE_GROUP }">[이전]</a></li>
						</ul>
					</c:if>
					<div class="text-center">
						<ul class="pagination">
							<c:forEach var="i" begin="${ startPage }" end="${ endPage }">
								<c:if test="${ i == currentPage }">
									<li class="disabled"><a href="#">${ i }</a></li>
								</c:if>
								<c:if test="${ i == 0 }">
									<li class="disabled"><a href="#">${ i }</a></li>
								</c:if>
								<c:if test="${ i !=0 && i != currentPage }">
									<li><a href="myPage?pageNum=${ i }">${ i }</a></li>
								</c:if>
							</c:forEach>
						</ul>
					</div>
					<c:if test="${ endPage < pageCount }">
						<ul class="pager">
							<li><a href="myPage?pageNum=${ startPage + PAGE_GROUP }">[다음]</a></li>
						</ul>
					</c:if>
				</div>
				<div class="modal-footer">
           		</div>
           	</div>
          </div>
        </div>
      </div>

 <!-- myPoint 모달 -->
   <div class="modal fade" id="myPoint" role="dialog" style="border-radius: 8px;">
      <div class="modal-dialog">
         <!-- Modal content-->
         <div class="modal-content">
            <div class="modal-header" style="font-size: 25px; background: #E4E3F3; color: #7092BE;border-radius: 8px;">
               <button type="button" class="close" data-dismiss="modal">&times;</button>
               <span><b>MY POINT</b></span>
            </div>
            <div class="modal-body">
            </div>
            <div class="modal-footer">
            </div>
          </div>
        </div>
      </div>

   <!-- 개인정보 수정 모달 -->
   <div class="modal fade" id="passwordCheck" role="dialog" style="border-radius: 8px;">
      <div class="modal-dialog">
         <!-- Modal content-->
         <div class="modal-content">
            <div class="modal-header" style="border-radius: 8px;">
               <button type="button" class="close" data-dismiss="modal">&times;</button>
               <h4 class="modal-title" style="text-align: center;">비밀번호 확인</h4>
               <p style="visibility: hidden;">1</p>
               <form id="passwordCheckForm" class="form-horizontal" role="form"
                  action="updateMemberInfoForm" method="post" target="myInfoFrame">
                  <div class="form-group">
                     <div class="col-sm-12 col-sm-12">
                        <input class="form-control" id="password" type="password"
                           value="" name="password" placeholder="password"> <input
                           type="hidden" name="loginUser" id="loginUser"
                           value="${ loginUser.email }" />
                     </div>
                  </div>
                  <div class="form-group">
                     <div class="col-sm-12 col-sm-12">
                        <button type="button" id="btnpasswordCheck"
                           class="btn btn-info btn-block">확인</button>
                     </div>
                  </div>
               </form>
            </div>
         </div>
      </div>
   </div>
