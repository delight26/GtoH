<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <script>
    function sessioncheck(loginUser){
    	if(loginUser==''){
    		$('#myModal').modal({
    		remote : 'loginform?page=aggro"'		
    		});
    	}else{
    		document.location.href="agrroboard";
    	}
    }
    </script>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="home">Project Call</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li><a href="YSRanking">전국랭킹</a></li>
        <li><a href="localRanking">지역랭킹</a></li>
		<li><a href="getNoticeList">공지사항</a></li>
		<li><a href="FreeBoardList">자유게시판</a></li>
		<li><a href="javascript:sessioncheck('${loginUser }')">도발게시판</a></li>
		<li><a href="hyunsu/ask/new">대결신청</a></li>
		<li><a href="fightResultBoardList">승부결과</a></li>
		<li><a href="productlist">포인트몰</a></li>
      </ul>
    </div>
  </div>
</nav>
