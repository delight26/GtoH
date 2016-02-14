<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/aggrocomment.js"></script>
<c:forEach var="c" items="${cList }" >
	<div class="col-lg-12 eachComment" style="background-color: #fafafa;" >
		<div class="img col-lg-1 col-md-1 col-sm-1 col-xs-1" >
		  	<img src="resources/uploadimages/${ member.profilPhoto }"  id="commentImg" />
		</div>
		<div class="col-lg-11 col-md-11 col-sm-11 col-xs-11">
		   <p>
		    <label class="plus-comment-name">${c.cWriter }</label>
		    <label class="plus-comment-date">
		    	<fmt:formatDate value="${c.writeDate }" pattern="yy-MM-dd" />
		    </label>
		    &nbsp;
		    <c:set var="email" value="${c.cEmail }" />
			<c:if test="${loginUser.email == email }">
			    <a href="javascript:commentupdate(${c.cNo }) ">수정</a>
			    <a href="javascript:commentdelete(${c.cNo }, ${c.bNo })">삭제</a>
		    </c:if>
		   </p>
		   <p>
		        ${c.cContent }
		   </p>
		 </div>
		 
		 <div class="img col-lg-1 col-md-1 col-sm-1 col-xs-1" ></div>
		 <div class="modif_content col-lg-11 col-md-11 col-sm-11 col-xs-11" id="modify${c.cNo }">
		   <p>
		        <textarea class="form-control" id="modifycomment${c.cNo }" rows="" cols="35"></textarea>
		        <a href="#" onclick="updatecomment(${c.cNo},${c.bNo })">수정</a>
			    <a href="javascript:modifycancel(${c.cNo })">취소</a>
		   </p>
		 </div>
		 
	 </div>
 </c:forEach>
 
 <c:if test="${ startPage > PAGE_GROUP }">
   <ul class="pager">
      <li><a href="agrroboard?pageNum=${ startPage - PAGE_GROUP }">[이전]</a></li>
   </ul>
</c:if>
<div class="text-center">
   <ul class="pagination">
      <c:forEach var="i" begin="${ startPage }" end="${ endPage }">
         <c:if test="${ i == currentPage && i != 0 }">
            <li class="disabled"><a>${ i }</a></li>
         </c:if>
         <c:if test="${ i != currentPage }">
            <li><a href="javascript:commentList(${ i })">${ i }</a></li>
         </c:if>
         <c:if test="${ i == 0 }">
 
         </c:if>
      </c:forEach>
   </ul>
</div>
<c:if test="${ endPage < pageCount }">
   <ul class="pager">
      <li><a href="productlist?pageNum=${ startPage + PAGE_GROUP }">[다음]</a></li>
   </ul>
</c:if>
 
</body>
</html>
<<<<<<< HEAD














=======
>>>>>>> refs/remotes/origin/master
