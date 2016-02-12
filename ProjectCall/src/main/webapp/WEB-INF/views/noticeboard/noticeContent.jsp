<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet"
   href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/boardContent.css">
<link rel="stylesheet"
   href="src/main/webapp/resources/css/boardContent.css">
<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
   src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script>
   function noticeDelete(no) {
      var conf = confirm("이 게시글을 삭제하시겠습니까?");

      if (conf) {
         location.href = "noticeDelete?no=" + no;
      }
   }
</script>
<input type="hidden" name="pageNum" value="${ pageNum }" />


<style>
.th {
   background-color: #F8F8FF;
   font-weight: bold;
}

.td {
   min-height: 30px;
}

.table {
   font-size: 18px;
}
</style>

<div class="content">
   <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
      <div class="col-lg-2 col-md-2 col-sm-3 col-xs-3 td th">글쓴이</div>
      <div class="col-lg-4 col-md-4 col-sm-3 col-xs-9 td">${ notice.frbWriter }</div>
      <div class="col-lg-2 col-md-2 col-sm-3 col-xs-3 th td">작성일</div>
      <div class="col-lg-4 col-md-4 col-sm-3 col-xs-9 td">
         <fmt:formatDate value="${ notice.frbWriteDate }" pattern="yyyy-MM-dd" />
      </div>
   </div>

   <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 td">
      <div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 th td">제목</div>
      <div class="col-lg-10 col-md-10 col-sm-10 col-xs-9 td">${ notice.frbTitle }</div>
   </div>

   <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 td">
      <div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 th td">내용</div>
      <div class="col-lg-10 col-md-10 col-sm-10 col-xs-9 td">
         <c:if test="${ notice.photo1 != null }">
            <img src="resources/images/photo1/${ notice.photo1 }" width="300px"
               height="300px" />
            <br />
         </c:if>
         ${ notice.frbContent }
      </div>
   </div>
</div>

<div class="board_btn">
   <a href="getNoticeList?pageNum=${ pageNum }"><img
      src="resources/images/btn_list.gif" width="70px"
      style="border-radius: 4px;" /></a>&nbsp;
   <c:if test="${ sessionScope.loginUser.nickName == 'admin' }">
      <img src="resources/images/btn_delete.gif"
         onclick="noticeDelete('${ notice.frbNo }')" width="50px"
         style="border-radius: 4px;" />&nbsp;
   <a href="noticeModifyForm?no=${ notice.frbNo }"><img
         src="resources/images/btn_modify.gif" width="50px"
         style="border-radius: 4px;" /></a>
   </c:if>
</div>

