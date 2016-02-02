<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<ul>
	<li><a href="YSRanking">랭킹확인</a></li>
	<li><a href="myPage?loginUser=${ loginUser.email }" >마이페이지</a></li>
	<li><a href="YSGetNote?toid=${ loginUser.nickName }&pageNum=1"
		onClick="window.open(this.href, '', 'width=400, height=430'); return false;">쪽지보기</a></li>
	<li id="noteCheck"><input type="hidden" value="${ loginUser.nickName }" id="nickname"></li>
	<li><a href="logout">로그아웃</a></li>
</ul>