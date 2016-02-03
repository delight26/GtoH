<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, target-densitydpi=medium-dpi">
<title>ProjectCall : 회원가입 </title>
<link rel="stylesheet" href="../../resources/css/join_pc.css" />
<link rel="stylesheet" href="../../resources/css/join.css" />
<script type="text/javascript" src="../../resources/js/clickcr.js"></script>
<script type="text/javascript" src="../../resources/js/join_kr.js"></script>
<script type="text/javascript" src="../../resources/js/rsaAll.js"></script>
<script type="text/javascript" src="../../resources/js/lua.js"></script>
<script type="text/javascript" src="../../resources/js/jquery.min.js"></script>
<script type="text/javascript" src="../../resources/js/aatlm.js"></script>
<script type="text/javascript" src="../../resources/js/aatlnm.js"></script>
<script type="text/javascript" src="../../resources/js/soundCaptcha.js"></script>
<script type="text/javascript" src="../../resources/js/createXhr.js"></script>
<script type="text/javascript" src="../../resources/js/idCheck.js"></script>

<script type="text/javascript">

//nClicks 전역변수
var nsc = "nid.join";
var ccsrv = "cc.projectCall.com";

var log1;
var log2;

$(document).ready(function() {
	log1 = new Aatlm();
	log1.sa(".join_form", "input:not(:radio, .int_join), a", ":not(:radio, .int_join), a");
	
	log2 = new Aatlnm();
	log2.sa(".join_form", "#id");
});

function getLenChar(texts) {
	texts = texts + "";
	return String.fromCharCode(texts.length);
}

function createRsaKey() {

}


function reCaptcha() {
	document.getElementById("captchaimg").src = document.getElementById("captchaimg").src + "1";
}



</script>
<meta name="decorator" content="PC_JOIN_UNREAL">
</head>

<!-- PC-ko_KR -->
<body class="">
<div id="wrap">

<!-- PC-ko_KR -->
	<!-- header -->
	<div id="header"><h1></h1>
<!-- 		<h1><a href="http://www.projectCall.com" class="h_logo">projectCall</a></h1>
 -->	</div>
	<!-- //header -->
	
	<!-- container -->
	<div id="container">
		
		<!-- content -->
		<div id="content">
			<h2 class="blind">ProjectCall 회원가입</h2>
			<div class="join_content">
				<div class="join_form">
				
<form id="join_form" method="post" action="step3" >		
				<fieldset class="join_from">						
					<legend class="blind">회원가입</legend>	
					<div class="row_group">
						<div id="idDiv" class="join_row">
							<span class="ps_box2 int_id">
								<input type="text" id="id" name="email" value="" maxlength="25" 								
								onkeyup="idcheck();"
								placeholder="EMAIL (사용하실 아이디 입니다.)" class="int"> 
							</span> 
							<!-- class:error e_info, error, error gm -->
							<span id = "idcheckLayer"></span>
						<a href="#" onClick="javascript:emailCheck();return true;" class="btn_c btn_mobile_submit">인증</a>
						</div>
						<div id="authnoDiv" class="join_row join_mobile_certify">
							<span class="ps_box int_mobile_certify"> 
								<input type="text" id="getCode" name="getCode" maxlength="6" value=""
									placeholder="인증번호" class="int">
								<label id="authnoLb" for="authno" class="lbl">인증번호</label>  
							</span>
							<span id = "sendCodeCheckLayer"></span>
							<div id="authnoMsg" class="error" style="display:none">필수 정보입니다.</div>
							<span id = "sendCodeCheckLayer"></span>
							<a href="#" onClick="javascript:getSendCodeCheck();return true;" class="btn_c btn_certify_submit">확인</a>
						</div>						
						<div id="pswd1Div" class="join_row">
							<span id="pswd1Img" class="ps_box int_pass">
								<input type="password" id="pswd1" name="pswd1" maxlength="16"
								onFocus="toggleLabel('pswd1Lb','pswd1','in');"
								onBlur="toggleLabel('pswd1Lb','pswd1','out');checkPswd1('check');"								
								onkeyup="checkShiftUp(event);"								
								onkeypress="checkCapslock(event);"
								onKeydown="checkShiftDownJoin(event);"								 
								placeholder="비밀번호" class="int"> 
								<label id="pswd1Lb" for="pswd1" class="lbl">비밀번호</label> <button type="button" title="delete" class="wrg">삭제</button>
							</span>							
							<div id="pswd1Msg" class="error" style="display:none">필수 정보입니다.</div>
						</div>
						<div id="pswd2Div" class="join_row">
							<!-- [D]normal상태 int_pass_check / 비밀번호 확인완료 int_pass_check2  -->							
							<span id="pswd2Img" class="ps_box int_pass_check">
								<input type="password" id="pswd2" name="pswd2" maxlength="16"
								onFocus="toggleLabel('pswd2Lb','pswd2','in');"
								onBlur="toggleLabel('pswd2Lb','pswd2','out');checkPswd2('check');"
								onkeyup="checkShiftUp(event);"								
								onkeypress="checkCapslock2(event);"
								onKeydown="checkShiftDown(event);"
								placeholder="비밀번호 재확인" class="int"> 
								<label id="pswd2Lb" for="pswd2" class="lbl">비밀번호 재확인</label> <button type="button"  title="delete" class="wrg">삭제</button>
							</span> 
							<div id="pswd2Msg" class="error" style="display:none">필수 정보입니다.</div>
						</div>
					</div>

					<div class="row_group">
						<div id="nmDiv" class="join_row">
							<span class="ps_box">
								<input type="text" id="nm" name="nm" maxlength="40" value=""
								onFocus="toggleLabel('nmLb','nm','in');"
								onBlur="toggleLabel('nmLb','nm','out');checkName('check')"
								placeholder="이름" class="int"> 
								<label id="nmLb" for="nm" class="lbl">이름</label> <button type="button" title="delete" class="wrg">삭제</button>
							</span>
							<div id="nmMsg" class="error" style="display:none">필수 정보입니다.</div>
						</div>
						<div id="nicknamediv" class="join_row">
							<span class="ps_box">
								<input type="text" id="nickname" name="nickname" maxlength="40" value=""
								placeholder="닉네임" class="int"
								onkeyup="nickNameCheck();"> 
								<label id="nicknameLB" for="nm" class="lbl">닉네임</label> 
							</span>
							<span id = "nickNameCheckLayer"></span>
						</div>						
						<div id="sexDiv" class="join_row join_sex">
							<span class="row_title blind"> 성별 </span>
							<span class="sex">
								<span class="jender"><input type="radio" id="man" name="sex" value="0" onClick="checkSex()" /><label id="manLb" for="man">남자 </label>
								</span><span class="jender"><input type="radio" id="woman" name="sex" value="1" onClick="checkSex()" /><label id="womanLb" for="woman">여자 </label></span>
							</span>
							<span id="sexMsg" class="error" style="display:none">필수 정보입니다.</span>
						</div>
						
						<div id="birthdayDiv" class="join_row join_birthday">
							<div class="join_birth">
								<div class="bir_title"><span>생일</span></div>
								<div class="bir_yy">
									<span class="ps_box">
										<input type="text" id="yy" maxlength="4" value=""
										name="yy"
										placeholder="년(4자)" class="int"> 
										<label id="yyLb" for="yy" class="lbl">년(4자)</label>
									</span>
								</div>
								<span class="cell">|</span>
								<div class="bir_mm">
									<span class="ps_box">
										<select id="mm" title="월" class="sel" name="mm">
											<option value="">월</option>
											<option value="1" >1</option>
										  	<option value="2" >2</option>
										  	<option value="3" >3</option>
										  	<option value="4" >4</option>
										  	<option value="5" >5</option>
				  						  	<option value="6" >6</option>
									  	 	<option value="7" >7</option>
										   	<option value="8" >8</option>
									  	 	<option value="9" >9</option>
									  	 	<option value="10" >10</option>
									  	 	<option value="11" >11</option>
										  	<option value="12" >12</option>
										</select>
									</span>
								</div>
								<span class="cell">|</span>
								<div class=" bir_dd">
									<span class="ps_box">
										<input type="text" id="dd" maxlength="2" value=""
										name="dd"
										placeholder="일" class="int"> 
										<label id="ddLb" for="dd" class="lbl">일</label> 
									</span>
								</div>
							</div>
							<span id="birthdayMsg" class="error" style="display:none">필수 정보입니다.</span>
						</div>						
					</div>
				</fieldset>
				<a id="bottom"></a>					
				<div class="error_ch"><span id="joinMsg" class="error" style="display:none">가입정보를 확인해주세요.</span></div>		
				<span class="btn_join"><input type="submit"  title="회원가입" alt="회원가입" value="가입하기" class="int_join"></span>						
			</form>
		</div>
				</div>
			</div>	

			
		</div>	
		<!-- //content -->
	</div>
	<!-- //container -->
	
<!--가상주민번호-아이핀 팝업페이지 호출시 필요한 form-->
<form name="form_ipin" method="post" action="">
    <input type="hidden" name="m" value="pubmain">
    <input type="hidden" name="enc_data" value="AgEEQTczOIlZCPToJW7dtYwABpE3gdozJ0SNWquwyHTdA0wI3HrdV4gNgOkHAvJL6XCz0kT1E+bZB7Rp496q6mHNUHy7rqbgyVe9JH0Rn1DXMZb+QW+inoC5YeBtZcTzzlvFf+Ey/blwkAHyhOihrMaS8FN9mTuT9VXtdA+UG9ql2AgNCDf1owdARyl5jvIrVFrY1V3JDfycfAXCHvNCcjy+1kX8bMOVeT80duz4GRNK1UNDUlUODpvESMh+1CUiT4g+Q7I0fU1U9QXcbUyAhYNK20uyHxjw9xMXiOz4FoWIi3Td/qge96vqgM+9PQejtTo9LNb9AQ==">
</form>	

<script type="text/javascript">
checkBrowser();

if( "Y" == "N" ) {
	mainSubmit(0);
}
</script>

	<!-- footer -->
	<div id="footer">
		<ul>
		<li><a href="http://www.projectCall.com/rules/service.html">이용약관</a></li>
		<li><strong><a href="http://www.projectCall.com/rules/privacy.html">개인정보취급방침</a></strong></li>
		<li><a href="http://www.projectCall.com/rules/disclaimer.html">책임의 한계와 법적고지</a></li>
		<li><a href="https://help.projectCall.com/support/service/main.nhn?serviceNo=532" target="_blank" >회원정보 고객센터 </a></li>
		</ul>
		<address>
			<em class="copy">Copyright</em> 
			<em class="u_cri">&copy;</em>
			<a href="http://nhncorp.com/" target="_blank" class="u_cra">projectCall Corp.</a> 
			<span class="all_r">All Rights Reserved.</span>
		</address>
	</div>
	<!-- //footer -->
	

</body>
</html>