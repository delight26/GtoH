<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<script type="text/javascript">
	function selfclose() {
		opener.parent.location.reload();
		self.close();
	}
</script>
<script
	src="${pageContext.request.contextPath}/resources/js/addMember.js"></script>
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
	<form action="loginresult" name="loginform" method="post"
		class="form-horizontal">
		<p class="ptag">UserEmail</p>
		<p>
			<input id="textinput" name="email" type="text"
				placeholder="example@gmail.com" class="form-control input-md">
		</p>
		<p class="ptag">PassWord</p>
		<p>
			<input id="passwordinput" name="pass" type="password"
				placeholder="Password" class="form-control input-md">
		</p>
		<p><a onclick="findIdPass();">Forgot ID/Pass? Click!</a></p>		
		<input type="hidden" name="page" value="${page }" /> <input
			type="hidden" name="quantity" value="${quantity }" /> <input
			type="hidden" name="pProductCode" value="${pProductCode}" />
		<button type="submit" class="btn btn-block btn-primary btn-warning">Sign In</button>
	</form>
</div>
<div class="modal-footer">
	Not Registered?
	<button type="button" class="btn btn-primary" onclick="addmember()">Register</button>
</div>