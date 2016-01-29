<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@  page  import="com.project.call.domain.FightBoard"%>

<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	${ sessionScope.loginUser }
	<a href="myPage?loginUser=${ sessionScope.loginUser }">마이페이지</a>
</body>
</html>














