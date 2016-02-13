<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<body>
         <div class="modal-content">
            <div class="modal-header" style="border-radius: 8px;">
               <button type="button" class="close" data-dismiss="modal">&times;</button>
               <h4 class="modal-title" style="text-align: center;">비밀번호 확인</h4>
               <p style="visibility: hidden;">1</p>
               <form id="passwordCheckForm" class="form-horizontal" role="form"
                  action="updateMemberInfoForm" method="post">
                  <div class="form-group">
                     <div class="col-sm-12 col-sm-12">
                        <input class="form-control" id="password" type="password"
                            name="password" placeholder="password"> <input
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
</body>
