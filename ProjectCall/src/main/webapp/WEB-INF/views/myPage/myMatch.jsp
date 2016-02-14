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
// $('#btnAddFightResultForm').on('click',function(){
// 	  var fightNumber = $('#fightNumber').val();
// 		$('#myPageModal').modal('hide');
// 		  $.ajax({
// 		        url: "addFightResultBoardForm",
// 		        type:"POST",
// 		        data:{"fightNumber": fightNumber},
// 		        dataType: "text",
// 		        success: function(responseData, statusText, xhr){
// 		        	var result = responseData;
// 		        	$('#myPageModal').modal({
// 		        		remote : $('.modal-content').html(result)
// 		        		});
// 		        },
// 		        error : function(xhr, statusText, responseData){
// 		           alert("error : " + statusText + "." + xhr.status+ "/ " + xhr.responseText);
// 		        }
// 		     });
// 	});
	
	function addFightResult(){
		var fightNumber = $('#fightNumber').val();
		  $.ajax({
		        url: "addFightResultBoardForm",
		        type:"GET",
		        data:{"fightNumber": fightNumber},
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
            <div class="modal-header" style="font-size: 25px; background: #E4E3F3; color: #7092BE;border-radius: 8px;">
               <button type="button" class="close" data-dismiss="modal">&times;</button>
               <span><b>MY MATCH</b></span>
            </div>
            <div class="modal-body">
				<div>
					<table id="matchModal">
						<tr>
							<td>닉네임</td>
							<td>${ loginUser.nickName }</td>
						</tr>
						<tr>
							<td>랭킹</td>
							<td>${ member.rank }위</td>
						</tr>
						<tr>
							<td>전적</td>
							<td>${ loginUser.win + loginUser.lose }전  ${ loginUser.win }승  ${ loginUser.lose }패</td>
						</tr>
						<tr>
							<td>승률</td>
							<td>${ winningRate }% </td>
						</tr>
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
			               <td><fmt:formatDate value="${ f.callDate }" pattern="yy-MM-dd" />
			               <input type="hidden" id="loginUser" value='${ loginUser.email }' /></td>
			               <td><fmt:formatDate value="${ f.resultDate }" pattern="yy-MM-dd" /></td>
			               <td>${ f.player1 }</td>
			               <td>${ f.player2 }</td>
			               <td><c:if test="${f.result == 0 }">
                     		결과를 등록해 주세요
                 		 </c:if> <c:if test="${f.result != 0 }">
                     		승인 대기중
                  		 </c:if></td>
               				<td><c:if test="${f.result == 0 }">
                     			<input type="button" value="승리" id="btnAddFightResultForm1"
                        				name="btnAddFightResultForm1" onclick="fightresultmyself(1,'${f.fightNumber}')"/>
                        		<input type="button" value="패배" id="btnAddFightResultForm0"
                        				name="btnAddFightResultForm0" onclick="fightresultmyself(0,'${f.fightNumber}')"/>
                     			<input type="hidden" id="fightNumber" value="${ f.fightNumber }" />
                  		 </c:if> <c:if test="${f.result != 0 }">
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
           		</div>
</body>
