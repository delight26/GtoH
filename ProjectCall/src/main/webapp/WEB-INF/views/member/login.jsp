<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<script type="text/javascript">
function selfclose(){
	opener.parent.location.reload();
	self.close();
}
</script>
<script src="${pageContext.request.contextPath}/resources/js/addMember.js"></script>
<style>
.ptag {
	text-align: left;
	text-weight: bold;
}
</style>
</head>
<div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h3 class="modal-title">Please Login!</h3>
        </div>
        <div class="modal-body">
        <form action="loginresult" name="loginform" method="post" class="form-horizontal">
          <p class="ptag">UserEmail</p>
          <p><input id="textinput" name="email" type="text" placeholder="example@gmail.com" class="form-control input-md"></p>
          <p class="ptag">PassWord</p>
          <p><input id="passwordinput" name="pass" type="password" placeholder="Password" class="form-control input-md"></p>
          <input type="hidden" name="page" value="${page }" />
          <input type="hidden" name="quantity" value="${quantity }"/>
          <input type="hidden" name="pProductCode" value="${pProductCode}" />
          <button type="submit" class="btn btn-block btn-primary btn-warning">Sign In</button>
          <button type="button" onclick="findIdPass();" class="btn btn-block btn-warning">Find Id/Pass</button>
        </form>
        </div>
        <div class="modal-footer">
         Not Registered?
            <button type="button" class="btn btn-primary" onclick="addmember()">Register</button>
        </div>
<%--       <div class="modal-header">
		<button type="button" class="close" data-dismiss="modal">x</button>
        <h3 class="modal-title" id="dataModalLabel">Please Login!</h3>
      </div>
      <div class="modal-body" >
      	<form action="loginresult" name="loginform" method="post" class="form-horizontal">
<!-- Text input-->
<div class="form-group">
  <p>Email</p>  
  <p><input id="textinput" name="email" type="text" placeholder="example@gmail.com" class="form-control input-md"></p> 
</div>
<!-- Password input-->
<div class="form-group">
  <p>Password</p>
    <p><input id="passwordinput" name="pass" type="password" placeholder="Password" class="form-control input-md"></p>
</div>
	<input type="hidden" name="pProductCode" value="${pProductCode}" />
		<button type="submit" class="btn btn-block btn-primary btn-warning">Sign In</button>
		</form>
      </div>
      <div class="modal-footer">
         Not Registered?
            <button type="button" class="btn btn-primary" onclick="addmember()">Register</button>
      </div>
 --%>
