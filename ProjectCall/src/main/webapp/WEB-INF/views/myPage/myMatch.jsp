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
<style>
#matchModal {
	background: #bdbdbd;
	color: white;
	width: 100%;
	border-radius: 10px;
	font-family: 'consolas';
}
#matchModal th {padding: 5px 0;}
#matchTable td {font-size: 10px;}
#matchTable {margin-top: 10px;}
.text_border {
	text-shadow: -1px 0 black, 0 1px black, 1px 0 black, 0 -1px black;
	}
  .graph { 
        position: relative; /* IE is dumb */
        padding: 2px; 
      font-size:11px;
      font-family:tahoma;
      margin-bottom:3px;
    }
    .graph .bar { 
        display: block;
        position: relative;
        height: 2em; 
        line-height: 2em;   
        text-align: left;
    }
    .graph .win {background: #5CD1E5;font-weight:bold;}
    .graph .lose {background: #FF9436;font-weight:bold;}
    .graph .r {text-align: right;color: white;font-size:11px;}
    .graph .l {text-align: left;color: white;font-size:11px;}
    /* .graph .bar span { position: absolute; left: 1em; } */
</style>
<body>
            <div class="modal-header" style="font-size: 25px; background: #E4E3F3; color: #7092BE;border-radius: 8px;">
               <button type="button" class="close" data-dismiss="modal">&times;</button>
               <span><b>MY MATCH</b></span>
            </div>
            <div class="modal-body">
				<div>
					<table id="matchModal">
						<tr>
							<th class="text_border">RANKING</th>
							<th class="text_border" style="font-size: 22px;"><i>${ loginUser.nickName }</i></th>
							<th class="text_border">WINNING<br/>RATE</th>
						</tr>
						<tr>
							<td style="width: 130px"><span style="font-size: 70px; font-weight:bold; color: #FFE08C" class="text_border"><i>${ member.rank }</i></span>위</td>
							<td>
								<table style="width: 100%">
									<tr><td><div class="graph"><span class="bar win" style="width: ${ (loginUser.win / (loginUser.win+loginUser.lose))*100 }%;"><span class="l">WIN ${ loginUser.win }</span></span></div></td></tr>
									<tr><td><div class="graph"><span class="bar lose" style="width: ${ (loginUser.lose / (loginUser.win+loginUser.lose))*100 }%;"><span class="l">LOSE ${ loginUser.lose }</span></span></div></td></tr>
								</table>
							</td>
							<td style="width: 150px"><span style="font-size: 45px; font-weight:bold; color: #FFE400" class="text_border">${ winningRate }%</span></td>
						</tr>
					</table>
					
					<table id="matchTable" class="table table-striped table-hover footable">
						<thead>
						<tr>
							<th>신청일</th>
							<th>시행일</th>
							<th>요청자</th>
							<th>수락자</th>
							<th>대결 결과</th>
							<th>결과 등록</th>
						</tr>
						</thead>
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
                     		등록완료 하셨습니다.
                  		 </c:if></td>
               				<td><c:if test="${f.result == 0 }">
                     			<input type="button" value="승리" id="btnAddFightResultForm1" class="btn btn-info btn-xs"
                        				name="btnAddFightResultForm1" onclick="fightresultmyself(1,'${f.fightNumber}')"/>
                        		<input type="button" value="패배" id="btnAddFightResultForm0" class="btn btn-danger btn-xs"
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
