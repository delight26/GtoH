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
	${ loginUser.email }
	<a href="myPage?loginUser=${ loginUser.email }">마이페이지</a>
	<a href="fightResultBoardList">대결결과 게시판</a>
</body>
</html>














