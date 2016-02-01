<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>술배틀 Login</title>


<script type="text/javascript">
function selfclose(){
	opener.parent.location.reload();
	self.close();
}
</script>

<script src="resources/js/addMember.js"></script>

</head>
<body>
	<form action="loginresult" name="loginform" method="post">
		<table>
			<tr>
				<th>아이디:</th>
				<td><input type="text" name="email" /></td>
			</tr>
			<tr>
				<th>비밀번호:</th>

				<td><input type="password" name="pass" /></td>

				<td><input type="submit" value="로그인" onclick="selfclose()"/></td>

				<td><input type="submit" value="로그인" /></td>
				<td><input type="button" value="회원가입" onclick="addmember()" /></td>


				<td>
					<input type="password" name="pass" />
					<input type="hidden" name="pProductCode" value="${pProductCode }" />
				</td>
				<td>
					<input type="submit" value="로그인" />
				</td>

			</tr>
		</table>
	</form>
</body>
</html>