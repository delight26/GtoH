<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="memberjoinresult.do" method = "post">
아이디 : <input type="text" name = "id"/><br/>
비밀번호 : <input type="password" name = "pass"/><br/>
이름 : <input type="text" name ="name"/><br/>
휴대폰 번호: <select name = "phone1">
<option value="010">010</option>
<option value="011">011</option>
<option value="016">016</option>
<option value="018">018</option>
</select> - 
<input type="text" name = "phone2"/> - 
<input type="text" name = "phone3"/><br/>
생년 월일 : <input type="date" name = "birthday"/><br/>
직업 : <input type="text" name = "job"/><br/>
주소 : <input type="text" name ="adress"/><br/>
<input type="submit" value="회원 가입"/>
<input type="button" value="취소" onclick="joinMember()"/>
</form>
</body>
</html>