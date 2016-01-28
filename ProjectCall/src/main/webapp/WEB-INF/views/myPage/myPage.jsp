<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<title>마이페이지</title>
</head>
<body>
	<h2>마이페이지</h2>
	
	<div>${ member.nickName }님의 정보</div>
	
	<div>내 랭킹 / 승률
		<div>${ member.level } / ${ winningRate } </div>
	</div>
	
	
	<div>대결 전적
		
		<table>
		
			<tr>
				<td>대결 신청일</td>
				<td>대결 시행일</td>
				<td>대결 요청자</td>
				<td>대결 수락자</td>
				<td>대결 결과</td>
			</tr>
			
			<c:forEach var="f" items="${ fightList }">
				<tr>
					<td>
						${ f.fbCallDate }
					</td>
					<td>
						${ f.fbResultDate }
					</td>
					<td>
						${ f.fbP1 }
					</td>
					<td>
						${ f.fbP2 }
					</td>
					<td>
						${ f.fbresult }
					</td>
				</tr>
			</c:forEach>
			
		</table>
		
		
	</div>
	<button id="btnUpdateMemberInfo" 
		onclick="window.location.href='updateMemberInfoForm?loginUser=${ sessionScope.loginUser }'">회원정보 수정</button>
	<button id="btnUpdateMemberInfo" 
		onclick="window.location.href='dropMember?loginUser=${ sessionScope.loginUser }'">회원탈퇴</button>
</body>
</html>














