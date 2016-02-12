<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<ul>
	<li><a href="">대결확인</a></li>
	<li><a href="myPage?loginUser=${ loginUser.email }" >MYPAGE</a></li>
	<li><a href="YSGetNote?toid=${ loginUser.nickName }&pageNum=1" 
		onClick="window.open(this.href, '쪽지함', 'width=400, height=340'); return false;">쪽지함</a></li>
	<li id="noteCheck"><input type="hidden" value="${ loginUser.nickName }" id="nickname"></li>
	<li><a href="logout">로그아웃</a></li>
</ul>