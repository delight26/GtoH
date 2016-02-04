<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/createXhr.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/idCheck.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/autoSearch.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="addAsk" method="post">
		<table>
			<tr>
				<th>신청자 : </th>
				<td><label>${loginUser.nickName }</label></td>
			</tr>
			<tr>
				<th>대결상대 : </th>
				<td><input type="text" id="toId" name="toId" placeholder="닉네임 검색" >
					<div id="listBox">
						<ul id="resultList"></ul>
					</div>
				</td>
			</tr>
			<tr>
				<th>대결일 : </th>
				<td><input type="date" id="fightDate" name="fightDate"></td>
			</tr>
			<tr>
				<th>대결장소 : </th>
				<td><input type="text" id="place" name="place" placeholder="대결요청장소"></td>
			</tr>
			<tr>
				<th>전하는말 : </th>
				<td><input type="text" id="tell" name="tell" placeholder="전하고 싶은말!!"></td>
			</tr>			
		</table>
		<input type="submit" value="신청">
		<input type="reset" value="취소">
	</form>
</body>
</html>