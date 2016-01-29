var isCapslock = false;

function alert_ko_KR(url) {
	var ua = window.navigator.userAgent.toLowerCase();
	if (/crios/.test(ua) && /ipad/.test(ua)) {
		alert("접속하신 브라우저에서는 휴대전화/유선전화 인증이 지원되지 않아, 가입을 진행할 수 없습니다. 다른 브라우저를 이용해 주세요.");
		return;
	}
	location.href = url;
	return;
}

function checkBrowser() {
	var Browser = {
		chk : navigator.userAgent.toLowerCase()
	};

	Browser = {
		ie7 : Browser.chk.indexOf('msie 7') != -1,
		ie8 : Browser.chk.indexOf('msie 8') != -1,
		ie9 : Browser.chk.indexOf('msie 9') != -1,
		ipad : Browser.chk.indexOf('ipad') != -1,
		iphone : Browser.chk.indexOf('iphone') != -1,
		android : Browser.chk.indexOf('android') != -1
	};

	if (Browser.ie7) {
		document.getElementsByTagName("body")[0].className = document.getElementsByTagName("body")[0].className + " ie7";
	} else if (Browser.ie8) {
		document.getElementsByTagName("body")[0].className = document.getElementsByTagName("body")[0].className + " ie8";
	} else if (Browser.ie9) {
		document.getElementsByTagName("body")[0].className = document.getElementsByTagName("body")[0].className + " ie9";
	} else if (Browser.ipad || Browser.iphone || Browser.android) {
		document.getElementsByTagName("body")[0].className = document.getElementsByTagName("body")[0].className + " t";
		document.getElementsByTagName("link")[1].href = "";
	}

}

function checkBrowserM() {
	var Browser = {
		chk : navigator.userAgent.toLowerCase()
	};

	Browser = {
		ie7 : Browser.chk.indexOf('msie 7') != -1,
		ie8 : Browser.chk.indexOf('msie 8') != -1,
		ie9 : Browser.chk.indexOf('msie 9') != -1,
		ipad : Browser.chk.indexOf('ipad') != -1,
		iphone : Browser.chk.indexOf('iphone') != -1,
		android : Browser.chk.indexOf('android') != -1
	};

	if (Browser.android) {
		document.getElementsByTagName("body")[0].className = document.getElementsByTagName("body")[0].className + " a";
	}

}

function changeLang(event) {

	var s = document.getElementById('langSelect');
	var lang = s.options[s.selectedIndex].value;

	if (lang == "ko_KR") {
		clickcr(this, 'lan.kr', '', '', event);
	} else if (lang == "en_US") {
		clickcr(this, 'lan.en', '', '', event);
	} else if (lang == "zh-Hans_CN") {
		clickcr(this, 'lan.zh-Hans', '', '', event);
	} else if (lang == "zh-Hant_TW") {
		clickcr(this, 'lan.zh-Hant', '', '', event);
	} else {
		clickcr(this, 'lan.en', '', '', event);
	}

	location.replace("https://nid.naver.com/user/join.html?lang=" + lang);
}

function changeLangM(event) {

	var s = document.getElementById('langSelect');
	var lang = s.options[s.selectedIndex].value;

	if (lang == "ko_KR") {
		clickcr(this, 'lan.kr', '', '', event);
	} else if (lang == "en_US") {
		clickcr(this, 'lan.en', '', '', event);
	} else if (lang == "zh-Hans_CN") {
		clickcr(this, 'lan.zh-Hans', '', '', event);
	} else if (lang == "zh-Hant_TW") {
		clickcr(this, 'lan.zh-Hant', '', '', event);
	} else {
		clickcr(this, 'lan.en', '', '', event);
	}

	location.replace("https://nid.naver.com/user/join.html?lang=" + lang + "&platform=mobile");
}

function checkTerms(event) {
	viewTerms();

	return true;
}

function setTerms() {
	var oDiv = document.getElementById("termsDiv");

	if (document.getElementById("chk_all").checked) {
		document.getElementById("termsService").checked = true;
		document.getElementById("termsPrivacy").checked = true;
		document.getElementById("termsLocation").checked = true;
		document.getElementById("termsEmail").checked = true;
	} else {
		document.getElementById("termsService").checked = false;
		document.getElementById("termsPrivacy").checked = false;
		document.getElementById("termsLocation").checked = false;
		document.getElementById("termsEmail").checked = false;
	}

	if (true) {
		oDiv.style.display = "block";
		location.hash = "bottom";
	}

	viewTerms();
	return true;
}

function viewTerms() {
	

	return true;
}

function submitAgree() {

	var res = true;
	var oMsg = document.getElementById("termsMsg");

	if (checkTerms('check') != true) {
		res = false;
	}

	if (res == false) {
		oMsg.style.display = "block";
		return false;
	} else {
		oMsg.style.display = "none";
	}

	document.join_form.submit();
	return true;
}

function submitDisagree() {

	location.href = "http://www.naver.com";
	return true;
}

function submitDisagreeM() {

	location.href = "http://m.naver.com";
	return true;
}

function toggleLabel(labelId, inputId, mode) {
	var oLabel = document.getElementById(labelId);
	var oInput = document.getElementById(inputId);
	if (mode == "in") {
		oLabel.className = "lbl focus";
	} else {
		if (oInput.value != "") {
			oLabel.className = "lbl focus";
		} else {
			oLabel.className = "lbl";
		}
	}
}

function toggleLabelM(labelId, inputId, mode) {
	return true;
}

function getXmlHttp() {
	var xmlhttp;
	try {
		xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
	} catch (e) {
		try {
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		} catch (E) {
			xmlhttp = false;
		}
	}
	if (!xmlhttp && typeof XMLHttpRequest != 'undefined') {
		xmlhttp = new XMLHttpRequest();
	}
	return xmlhttp;
}

var idFlag = false;
var pwFlag = false;

/*$("#id").on("keyup", function(){

    var id = $("#id").val();
    
    $.ajax({
       url: "hyunsu/checkId",
       type:"POST",
       data: {"id" : id },
       dataType: "text",
       success: function(responseData, statusText, xhr){
          var result = responseData;
          
          if(id.length <=3 || id.length >20){
             $("#check_Id_result").text("아이디는 4글자 이상 20자 이하만 사용 가능합니다.").css("color", "red");
             $("#joinSubmit").attr("disabled", "disabled");
          }else {
             if(result >= 1){
                $("#check_Id_result").text("사용이 불가능한 아이디 입니다.").css("color", "red");
                $("#joinSubmit").attr("disabled", "disabled");
             }else if(result == 0){
                $("#check_Id_result").text("사용 가능한 아이디 입니다.").css("color", "blue");
                $("#joinSubmit").removeAttr("disabled");
             }
          }
       },
       error : function(xhr, statusText, responseData){
          alert("error : " + statusText + "." + xhr.status+ "/ " + xhr.responseText);
       }
    });
    
 });*/

// space 가 있으면 true, 없으면 false
function checkSpace(str) {
	if (str.search(/\s/) != -1) {
		return true;
	} else {
		return false;
	}
}

function isValidPasswd(str) {
	var cnt = 0;
	if (str == "") {
		return false;
	}

	/* check whether input value is included space or not */
	var retVal = checkSpace(str);
	if (retVal) {
		return false;
	}
	if (str.length < 6) {
		return false;
	}
	for (var i = 0; i < str.length; ++i) {
		if (str.charAt(0) == str.substring(i, i + 1))
			++cnt;
	}
	if (cnt == str.length) {
		return false;
	}

	var isPW = /^[A-Za-z0-9`\-=\\\[\];',\./~!@#\$%\^&\*\(\)_\+|\{\}:"<>\?]{6,16}$/;
	if (!isPW.test(str)) {
		return false;
	}

	return true;
}

var isShift = false;
function checkShiftUp(e) {
	if (e.which && e.which == 16) {
		isShift = false;
	}
}

function checkShiftDown(e) {
	if (e.which && e.which == 16) {
		isShift = true;
	}
}

function checkCapslock(e) {
	var myKeyCode = 0;
	var myShiftKey = false;
	if (window.event) { // IE
		myKeyCode = e.keyCode;
		myShiftKey = e.shiftKey;
	} else if (e.which) { // netscape ff opera
		myKeyCode = e.which;
		myShiftKey = isShift;
	}

	var oMsg = document.getElementById("pswd1Msg");

	if ((myKeyCode >= 65 && myKeyCode <= 90) && !myShiftKey) {
		oMsg.style.display = "block";
		oMsg.className = "error";
		oMsg.innerHTML = "Caps Lock이 켜져 있습니다.";
	} else if ((myKeyCode >= 97 && myKeyCode <= 122) && myShiftKey) {
		oMsg.style.display = "block";
		oMsg.className = "error";
		oMsg.innerHTML = "Caps Lock이 켜져 있습니다.";
	} else {
		oMsg.style.display = "none";
	}
}

function checkCapslock2(e) {
	var myKeyCode = 0;
	var myShiftKey = false;
	if (window.event) { // IE
		myKeyCode = e.keyCode;
		myShiftKey = e.shiftKey;
	} else if (e.which) { // netscape ff opera
		myKeyCode = e.which;
		myShiftKey = isShift;
	}

	var oMsg = document.getElementById("pswd2Msg");

	if ((myKeyCode >= 65 && myKeyCode <= 90) && !myShiftKey) {
		oMsg.style.display = "block";
		oMsg.className = "error";
		oMsg.innerHTML = "Caps Lock이 켜져 있습니다.";
	} else if ((myKeyCode >= 97 && myKeyCode <= 122) && myShiftKey) {
		oMsg.style.display = "block";
		oMsg.className = "error";
		oMsg.innerHTML = "Caps Lock이 켜져 있습니다.";
	} else {
		oMsg.style.display = "none";
	}
}

function checkPswd1(event) {
	checkPswd2("check");
	createRsaKey();

	var id = document.getElementById("id").value;
	var pw = document.getElementById("pswd1").value;
	var oMsg = document.getElementById("pswd1Msg");
	var oImg = document.getElementById("pswd1Img");

	if (pw == "") {
		oImg.className = "ps_box int_pass";
		oMsg.style.display = "block";
		oMsg.className = "error";
		oMsg.innerHTML = "필수 정보입니다.";
		lua_do('join_kr'+document.getElementById("platform").value,'',ERROR_PSWD1_REQUIRED, document.getElementById("token_sjoin").value, true,'');
		return false;
	}
	if (isValidPasswd(pw) != true) {
		oImg.className = "ps_box int_pass_step1";
		oMsg.style.display = "block";
		oMsg.className = "error e_info";
		oMsg.innerHTML = "6~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.";
		lua_do('join_kr'+document.getElementById("platform").value,'',ERROR_FORMAT_PSWD1, document.getElementById("token_sjoin").value, true,'');
		return false;
	}

	pwFlag = false;
	try {
		var xmlhttp = getXmlHttp();
		xmlhttp.open("GET", "/user2/joinAjax.nhn?m=checkPswd&id=" + escape(encodeURIComponent(id)) + "&pw=" + escape(encodeURIComponent(pw)));
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4) {
				var result = xmlhttp.responseText.substr(4, 999);				
				if (result == 1) {
					oImg.className = "ps_box int_pass_step1";
					oMsg.style.display = "block";
					oMsg.className = "error e_info";
					oMsg.innerHTML = "6~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.";
					lua_do('join_kr'+document.getElementById("platform").value,'',ERROR_FORMAT_PSWD1, document.getElementById("token_sjoin").value, true,'');
					return false;
				} else if (result == 2) {
					oImg.className = "ps_box int_pass_step2";
					oMsg.style.display = "block";
					oMsg.className = "error e_info";
					oMsg.innerHTML = "6~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.";
					lua_do('join_kr'+document.getElementById("platform").value,'',ERROR_FORMAT_PSWD1, document.getElementById("token_sjoin").value, true,'');
				} else if (result == 3) {
					oImg.className = "ps_box int_pass_step3";
					oMsg.style.display = "none";
				} else if (result == 4) {
					oImg.className = "ps_box int_pass_step4";
					oMsg.style.display = "none";
				} else {
					oImg.className = "ps_box int_pass";
					oMsg.style.display = "none";
				}
				pwFlag = true;
				return true;
			}
		};
		xmlhttp.send(null);
	} catch (e) {
		if (window.bridgeGotTime) {
			throw e;
		}
	}

	return true;
}

function checkPswd2(event) {
	var pswd1 = document.getElementById("pswd1").value;
	var pswd2 = document.getElementById("pswd2").value;
	var oMsg = document.getElementById("pswd2Msg");
	var oImg = document.getElementById("pswd2Img");

	if (pswd2 == "") {
		oImg.className = "ps_box int_pass_check";
		oMsg.style.display = "block";
		oMsg.innerHTML = "필수 정보입니다.";
//		lua_do('join_kr'+document.getElementById("platform").value,'',ERROR_PSWD2_REQUIRED, document.getElementById("token_sjoin").value, true,'');
		return false;
	}
	if (pswd1 != pswd2) {
		oImg.className = "ps_box int_pass_check";
		oMsg.style.display = "block";
		oMsg.innerHTML = "비밀번호가 일치하지 않습니다.";
		document.getElementById("pswd2").value = "";
		lua_do('join_kr'+document.getElementById("platform").value,'',ERROR_FORMAT_PSWD2, document.getElementById("token_sjoin").value, true,'');
		return false;
	} else {
		oImg.className = "ps_box int_pass_check2";
		oMsg.style.display = "none";
		return true;
	}

	return true;
}

function checkName(event) {
	toggleLabel('nmLb', 'nm', 'out');

	var nm = document.getElementById("nm").value;
	var oMsg = document.getElementById("nmMsg");

	if (nm == "") {
		oMsg.style.display = "block";
		oMsg.innerHTML = "필수 정보입니다.";
		lua_do('join_kr'+document.getElementById("platform").value,'',ERROR_NAME_REQUIRED, document.getElementById("token_sjoin").value, true,'');
		return false;
	}

	var nonchar = /[^가-힣ㄱ-ㅎㅏ-ㅣa-zA-Z0-9]/gi;
	if (nonchar.test(nm)) {
		oMsg.style.display = "block";
		oMsg.innerHTML = "이름에는 한글, 영문 대소문자를 이용해 주세요.";
		lua_do('join_kr'+document.getElementById("platform").value,'',ERROR_NAME_FORMAT, document.getElementById("token_sjoin").value, true,'');
		return false;
	}

	if (true) {
		oMsg.style.display = "none";
		return true;
	}

	return true;
}

function checkSex(event) {
	var man = document.getElementById("man");
	var woman = document.getElementById("woman");
	var oMsg = document.getElementById("sexMsg");
	//var oDiv = document.getElementById("sexDiv");
	//oDiv.className = "join_row join_sex join_check";
	
	if (man.checked == false && woman.checked == false) {
		oMsg.style.display = "block";
		oMsg.innerHTML = "필수 정보입니다.";
		lua_do('join_kr'+document.getElementById("platform").value,'',ERROR_NO_VALUE_SEX, document.getElementById("token_sjoin").value, true,'');
		return false;
	}

	if (man.checked) {
		document.getElementById("manLb").className = "on";
		document.getElementById("womanLb").className = "";
	}
	if (woman.checked) {
		document.getElementById("manLb").className = "";
		document.getElementById("womanLb").className = "on";
	}

	if (true) {
		oMsg.style.display = "none";
		return true;
	}

	return true;
}

function calcAge(birth) {
	var date = new Date();
	var year = date.getFullYear();
	var month = (date.getMonth() + 1);
	var day = date.getDate();
	if (month < 10)
		month = '0' + month;
	if (day < 10)
		day = '0' + day;
	var monthDay = month + '' + day;

	birth = birth.replace('-', '').replace('-', '');
	var birthdayy = birth.substr(0, 4);
	var birthdaymd = birth.substr(4, 4);

	var age = monthDay < birthdaymd ? year - birthdayy - 1 : year - birthdayy;
	return age;
}

function isValidDate(param) {
	try {
		param = param.replace(/-/g, '');

		// 자리수가 맞지않을때
		if (isNaN(param) || param.length != 8) {
			return false;
		}

		var year = Number(param.substring(0, 4));
		var month = Number(param.substring(4, 6));
		var day = Number(param.substring(6, 8));

		if (month < 1 || month > 12) {
			return false;
		}

		var maxDaysInMonth = [ 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 ];
		var maxDay = maxDaysInMonth[month - 1];

		// 윤년 체크
		if (month == 2 && (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)) {
			maxDay = 29;
		}

		if (day <= 0 || day > maxDay) {
			return false;
		}
		return true;

	} catch (err) {
		return false;
	}
	;
}

function checkBirthday(event) {
	toggleLabel('yyLb', 'yy', 'out');
	toggleLabel('ddLb', 'dd', 'out');

	var birthday = "";
	var yy = document.getElementById("yy").value;
	var s = document.getElementById('mm');
	var mm = s.options[s.selectedIndex].value;
	var dd = document.getElementById("dd").value;
	var oMsg = document.getElementById("birthdayMsg");

	if (yy == "" && mm == "" && dd == "") {
		oMsg.style.display = "block";
		oMsg.innerHTML = "태어난 년도 4자리를 정확하게 입력하세요.";
		return false;
	}
	
	if (mm.length == 1) {
		mm = "0" + mm;
	}
	if (dd.length == 1) {
		dd = "0" + dd;
	}
	
	if(yy == "") {
		oMsg.style.display = "block";
		oMsg.innerHTML = "태어난 년도 4자리를 정확하게 입력하세요.";
		lua_do('join_kr'+document.getElementById("platform").value,'',ERROR_NO_VALUE_YEAR, document.getElementById("token_sjoin").value, true,'');
		return false;
	}
	if(yy.length != 4 || yy.indexOf('e') != -1 || yy.indexOf('E') != -1) {
		oMsg.style.display = "block";
		oMsg.innerHTML = "태어난 년도 4자리를 정확하게 입력하세요.";
		lua_do('join_kr'+document.getElementById("platform").value,'',ERROR_FORMAT_YEAR, document.getElementById("token_sjoin").value, true,'');
		return false;
	}
	if(mm == "") {
		oMsg.style.display = "block";
		oMsg.innerHTML = "태어난 월을 선택하세요.";
		lua_do('join_kr'+document.getElementById("platform").value,'',ERROR_NO_VALUE_MONTH, document.getElementById("token_sjoin").value, true,'');
		return false;
	}
	if(dd == "") {
		oMsg.style.display = "block";
		oMsg.innerHTML = "태어난 일(날짜) 2자리를 정확하게 입력하세요.";
		lua_do('join_kr'+document.getElementById("platform").value,'',ERROR_NO_VALUE_DAY, document.getElementById("token_sjoin").value, true,'');
		return false;
	}
	if(dd.length != 2 || dd.indexOf('e') != -1 || dd.indexOf('E') != -1) {
		oMsg.style.display = "block";
		oMsg.innerHTML = "태어난 일(날짜) 2자리를 정확하게 입력하세요.";
		lua_do('join_kr'+document.getElementById("platform").value,'',ERROR_FORMAT_DAY, document.getElementById("token_sjoin").value, true,'');
		return false;
	}
	
	birthday = yy + mm + dd;	

	if (!isValidDate(birthday)) {
		oMsg.style.display = "block";
		oMsg.innerHTML = "생년월일을 다시 확인해주세요.";
		lua_do('join_kr'+document.getElementById("platform").value,'',ERROR_FORMAT_DATE, document.getElementById("token_sjoin").value, true,'');
		return false;
	}
	document.getElementById("birthday").value = birthday;

	var age = calcAge(birthday);
	if (age < 0) {
		oMsg.style.display = "block";
		oMsg.innerHTML = "미래에서 오셨군요. ^^";
		lua_do('join_kr'+document.getElementById("platform").value,'',ERROR_FORMAT_DATE, document.getElementById("token_sjoin").value, true,'');
		return false;
	} else if (age >= 100) {
		oMsg.style.display = "block";
		oMsg.innerHTML = "정말이세요?";
		lua_do('join_kr'+document.getElementById("platform").value,'',ERROR_FORMAT_DATE, document.getElementById("token_sjoin").value, true,'');
		return false;
	} else if (age < 14) {
		oMsg.style.display = "block";
		oMsg.innerHTML = "만 14세 미만의 어린이는 보호자 동의가 필요합니다.";

		if (document.getElementById("joinMode").value == "unreal") {
			viewJuniverMobileTab();
		}
		return true;
	} else {
		oMsg.style.display = "none";

		viewUnrealMobileTab();
		return true;
	}

	return true;
}

function checkEmail(event) {
	toggleLabel('emailLb', 'email', 'out');

	var id = document.getElementById("id").value;
	var email = document.getElementById("email").value;
	var oMsg = document.getElementById("emailMsg");

	if (email == "") {
		oMsg.style.display = "none";
		return true;
	}

	var isEmail = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	var isHan = /[ㄱ-ㅎ가-힣]/g;
	if (!isEmail.test(email) || isHan.test(email)) {
		oMsg.style.display = "block";
		oMsg.innerHTML = "이메일 주소를 다시 확인해주세요.";
		lua_do('join_kr'+document.getElementById("platform").value,'',ERROR_FORMAT_EMAIL, document.getElementById("token_sjoin").value, true,'');
		return false;
	}

	if (email == id + "@naver.com") {
		oMsg.style.display = "block";
		oMsg.innerHTML = "가입중인 아이디는 사용하실 수 없습니다.";
		lua_do('join_kr'+document.getElementById("platform").value,'',ERROR_FORMAT_EMAIL, document.getElementById("token_sjoin").value, true,'');
		return false;
	}

	if (true) {
		oMsg.style.display = "none";
		return true;
	}
	return true;
}

function setNationNo(obj) {
	var s = obj;
	var nationCd = s.options[s.selectedIndex].value;

	document.getElementById("nationMsg").innerHTML = "+" + nationCd;
	document.getElementById("nationNo").value = nationCd;

	return true;
}

function checkMobno(event) {
	toggleLabel('mobnoLb', 'mobno', 'out');

	var mobno = document.getElementById("mobno").value;
	var oMsg = document.getElementById("mobnoMsg");
	var old_mobno = document.getElementById("old_mobno").value;

	if (mobno == "") {
		oMsg.style.display = "block";
		oMsg.innerHTML = "필수 정보입니다.";
		lua_do('join_kr'+document.getElementById("platform").value,'',ERROR_NO_VALUE_PHONENO, document.getElementById("token_sjoin").value, true,'');
		return false;
	}
	
	if( old_mobno != "" && old_mobno != mobno) {
		oMsg.style.display = "block";
		oMsg.innerHTML = "인증을 다시 수행해 주세요.";
		
		document.getElementById("authno").value = "";
		document.getElementById("old_mobno").value = "";
		document.getElementById("old_authno").value = "";
		document.getElementById("authFlag").value = "N";
		
		toggleLabel('authnoLb','authno','out');
		checkAuthno('check');
		return false;
	}	
	

	if (true) {
		oMsg.style.display = "none";
		return true;
	}

	return true;
}

function checkAuthno(event) {
	toggleLabel('authnoLb','authno','out');
	
	var authno = document.getElementById("authno").value;
	var oMsg = document.getElementById("authnoMsg");
	var old_authno = document.getElementById("old_authno").value;
	
	if (authno == "" || document.getElementById("authFlag").value != "Y") {
		oMsg.style.display = "block";
		oMsg.className = "error";
		oMsg.innerHTML = "인증이 필요합니다.";
		lua_do('join_kr'+document.getElementById("platform").value,'',ERROR_NO_VALUE_AUTHNO, document.getElementById("token_sjoin").value, true,'');
		return false;
	}
	
	if( old_authno != "" && document.getElementById("authFlag").value == "Y" ) {
		oMsg.style.display = "block";
		oMsg.className = "error gm";
		oMsg.innerHTML = "인증된 상태입니다.";
		return true;
	}

	if (true) {
		oMsg.style.display = "none";
		return true;
	}

	return true;
}

function sendSmsButton() {
	var nationNo = document.getElementById("nationNo").value;
	var mobno = document.getElementById("mobno").value;
	var key = document.getElementById("token_sjoin").value;
	var oMsg = document.getElementById("mobnoMsg");

	mobno = mobno.split("-").join("");
	document.getElementById("mobno").value = mobno;

	document.getElementById("authFlag").value = "N";
	

	
	
	try {
		var xmlhttp = getXmlHttp();
		xmlhttp.open("GET", "/user2/joinAjax.nhn?m=sendAuthno&nationNo=" + nationNo + "&mobno=" + mobno + "&key=" + key);
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4) {
				var result = xmlhttp.responseText.substr(4, 999);
				oMsg.style.display = "block";
				
				if (result == "S") {
					oMsg.className = "error gm";
					oMsg.innerHTML = "인증번호를 발송했습니다.<br>인증번호가 오지 않으면 입력하신 정보가 정확한지 확인하여 주세요.";
					return true;
				} else if (result == "S_ARS") {
					oMsg.className = "error gm";
					oMsg.innerHTML = "전화를 걸고 있습니다.<br>통신사 사정에 따라 연결이 지연될 수 있으니 잠시만 기다려 주세요.";
					return true;	
				} else if (result == "Dur") {
					oMsg.className = "error";
					oMsg.innerHTML = "동일한 인증수단은 계속해서 사용할 수 없습니다.";
					lua_do('join_kr'+document.getElementById("platform").value,'',ERROR_AUTH_REQUIRED, document.getElementById("token_sjoin").value, true,'');
					return false;
				} else if (result == "Live") {
					oMsg.className = "error";
					oMsg.innerHTML = "해당 번호는 더 이상 가입이 불가능합니다.";
					lua_do('join_kr'+document.getElementById("platform").value,'',ERROR_AUTH_REQUIRED, document.getElementById("token_sjoin").value, true,'');
					return false;
				} else if (result == "Dupl") {
					oMsg.className = "error";
					oMsg.innerHTML = "등록 가능한 아이디 수를 초과하였습니다.";
					lua_do('join_kr'+document.getElementById("platform").value,'',ERROR_AUTH_REQUIRED, document.getElementById("token_sjoin").value, true,'');
					return false;
				} else {
					oMsg.className = "error";
					oMsg.innerHTML = "전화번호를 다시 확인해주세요.";
					lua_do('join_kr'+document.getElementById("platform").value,'',ERROR_AUTH_REQUIRED, document.getElementById("token_sjoin").value, true,'');
					return false;
				}
			}
		};
		xmlhttp.send(null);
	} catch (e) {
		if (window.bridgeGotTime) {
			throw e;
		}
	}

	return false;
}

function checkAuthnoButton() {
	var authno = document.getElementById("authno").value;
	var key = document.getElementById("token_sjoin").value;
	var oMsg = document.getElementById("authnoMsg");

	try {
		var xmlhttp = getXmlHttp();
		xmlhttp.open("GET", "/user2/joinAjax.nhn?m=checkAuthno&authno=" + authno + "&key=" + key);
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4) {
				var result = xmlhttp.responseText.substr(4, 999);

				oMsg.style.display = "block";
				if (result == "S") {
					oMsg.className = "error gm";
					oMsg.innerHTML = "인증이 성공했습니다.";
					document.getElementById("authFlag").value = "Y";
					return true;
				} else if (result == "Cnt") {
					oMsg.className = "error";
					oMsg.innerHTML = "인증을 다시 진행해주세요.";
					lua_do('join_kr'+document.getElementById("platform").value,'',ERROR_AUTH_REQUIRED, document.getElementById("token_sjoin").value, true,'');
					return false;
				} else {
					oMsg.className = "error";
					oMsg.innerHTML = "인증번호를 다시 확인해주세요.";
					lua_do('join_kr'+document.getElementById("platform").value,'',ERROR_AUTH_REQUIRED, document.getElementById("token_sjoin").value, true,'');
					return false;
				}
			}
		};
		xmlhttp.send(null);
	} catch (e) {
		if (window.bridgeGotTime) {
			throw e;
		}
	}

	return true;
}

function viewUnrealMobileTab() {
	document.getElementById("joinMode").value = "unreal";
	document.getElementById("mobDiv").style.display = "block";
	document.getElementById("jmobDiv").style.display = "none";
	document.getElementById("jipinDiv").style.display = "none";
	return true;
}

function viewJuniverMobileTab() {

	document.getElementById("joinMode").value = "juniverMobile";
	document.getElementById("mobDiv").style.display = "none";
	document.getElementById("jmobDiv").style.display = "block";
	document.getElementById("jipinDiv").style.display = "none";
	return true;
}

function viewJuniverIpinTab() {

	document.getElementById("joinMode").value = "juniverIpin";
	document.getElementById("mobDiv").style.display = "none";
	document.getElementById("jmobDiv").style.display = "none";
	document.getElementById("jipinDiv").style.display = "block";
	return true;
}

function checkPrtsName(event) {
	toggleLabel('pnmLb', 'pnm', 'out');

	var pnm = document.getElementById("pnm").value;
	var oMsg = document.getElementById("pnmMsg");

	if (pnm == "") {
		oMsg.style.display = "block";
		oMsg.innerHTML = "필수 정보입니다.";
		return false;
	}

	if (true) {
		oMsg.style.display = "none";
		return true;
	}
	return true;
}

function checkPrtsSex(event) {
	var pman = document.getElementById("pman");
	var pwoman = document.getElementById("pwoman");
	var oMsg = document.getElementById("psexMsg");
	var oDiv = document.getElementById("psexDiv");

	//oDiv.className = "join_row join_sex join_check";
	if (pman.checked == false && pwoman.checked == false) {
		oMsg.style.display = "block";
		oMsg.innerHTML = "필수 정보입니다.";
		return false;
	}

	if (pman.checked) {
		document.getElementById("pmanLb").className = "on";
		document.getElementById("pwomanLb").className = "";
	}
	if (pwoman.checked) {
		document.getElementById("pmanLb").className = "";
		document.getElementById("pwomanLb").className = "on";
	}

	if (true) {
		oMsg.style.display = "none";
		return true;
	}
	return true;
}

function checkPrtsBirthday(event) {
	toggleLabel('pyyLb', 'pyy', 'out');
	toggleLabel('pddLb', 'pdd', 'out');

	var birthday = document.getElementById("birthday").value;
	var pbirthday = document.getElementById("pbirthday").value;
	var pyy = document.getElementById("pyy").value;
	var s = document.getElementById('pmm');
	var pmm = s.options[s.selectedIndex].value;
	var pdd = document.getElementById("pdd").value;
	var oMsg = document.getElementById("pbirthdayMsg");

	if (pyy == "" && pmm == "" && pdd == "") {
		oMsg.style.display = "block";
		oMsg.innerHTML = "필수 정보입니다.";
		return false;
	}
	if (pyy == "" || pmm == "" || pdd == "" || pdd.indexOf('e') != -1 || pdd.indexOf('E') != -1) {
		oMsg.style.display = "block";
		oMsg.innerHTML = "생년월일을 정확히 입력해주세요.";
		return false;
	}

	if (pmm.length == 1) {
		pmm = "0" + pmm;
	}
	if (pdd.length == 1) {
		pdd = "0" + pdd;
	}
	pbirthday = pyy + pmm + pdd;

	if (!isValidDate(pbirthday)) {
		oMsg.style.display = "block";
		oMsg.innerHTML = "생년월일을 다시 확인해주세요.";
		return false;
	}
	document.getElementById("pbirthday").value = pbirthday;

	var age = calcAge(birthday);
	var page = calcAge(pbirthday);
	if (page < 0) {
		oMsg.style.display = "block";
		oMsg.innerHTML = "미래에서 오셨군요. ^^";
		return false;
	} else if (page >= 100) {
		oMsg.style.display = "block";
		oMsg.innerHTML = "정말이세요?";
		return false;
	} else if (page - age < 19) {
		oMsg.style.display = "block";
		oMsg.innerHTML = "보호자(법정대리인) 동의 기준이 맞지 않습니다.";
		return false;
	} else {
		oMsg.style.display = "none";
		return true;
	}
	return true;
}


function checkPrtsTelecom()
{
	var s = document.getElementById('ptelecomSel');
	var ptelecom = s.options[s.selectedIndex].value;
	var oDiv = document.getElementById("mvnoDiv");
	
	document.getElementById('ptelecom').value = ptelecom;
	
	if( ptelecom == "MVNO" ) {
		oDiv.style.display = "block";
		document.getElementById('ptelecom').value = "SKR";
	} else {
		oDiv.style.display = "none";
	}

	return true;
}

function checkMvno(telecom) {

	if (telecom == "SKT") {
		document.getElementById('ptelecom').value = "SKR";
		document.getElementById("mvno_sk").checked = true;		
		document.getElementById("mvno_skLb").className = "on";
		document.getElementById("mvno_ktLb").className = "";
		document.getElementById("mvno_lgLb").className = "";

	} else if(telecom == "KTF") {
		document.getElementById('ptelecom').value = "KTR";
		document.getElementById("mvno_kt").checked = true;		
		document.getElementById("mvno_skLb").className = "";
		document.getElementById("mvno_ktLb").className = "on";
		document.getElementById("mvno_lgLb").className = "";

	} else if(telecom == "LGT") {
		document.getElementById('ptelecom').value = "LGR";
		document.getElementById("mvno_lg").checked = true;
		document.getElementById("mvno_skLb").className = "";
		document.getElementById("mvno_ktLb").className = "";
		document.getElementById("mvno_lgLb").className = "on";
	} else if(telecom == "CLOSE") {
		return true; 
	}else{
		document.getElementById('ptelecom').value = "SKR";
		document.getElementById("mvno_sk").checked = true;
		document.getElementById("mvno_skLb").className = "on";
		document.getElementById("mvno_ktLb").className = "";
		document.getElementById("mvno_lgLb").className = "";
	}

	return true;
}

var mvnoFlag = "OFF";
function showMvnoLayer(mvnoCd) {
	
	if (mvnoFlag =="OFF") {
		document.getElementById('mvno_layer').style.display="block";
		mvnoFlag = "ON";
	} else {
		checkMvno(mvnoCd);
		document.getElementById('mvno_layer').style.display="none";
		mvnoFlag = "OFF";
	}
}

function checkPrtsMobno(event) {
	toggleLabel('pmobnoLb', 'pmobno', 'out');

	var pmobno = document.getElementById("pmobno").value;
	var oMsg = document.getElementById("pmobnoMsg");
	var old_pmobno = document.getElementById("old_pmobno").value;

	if (pmobno == "") {
		oMsg.style.display = "block";
		oMsg.innerHTML = "필수 정보입니다.";
		return false;
	}
	
	if( old_pmobno != "" && old_pmobno != pmobno) {
		oMsg.style.display = "block";
		oMsg.innerHTML = "인증을 다시 수행해 주세요.";
		
		document.getElementById("pauthno").value = "";
		document.getElementById("old_pmobno").value = "";
		document.getElementById("old_authno").value = "";
		document.getElementById("authFlag").value = "N";
		document.getElementById("pterms").checked = false;
		
		toggleLabel('pauthnoLb','pauthno','out');
		checkPrtsAuthno('check');
		return false;
	}

	if (true) {
		oMsg.style.display = "none";
		return true;
	}
	return true;
}

function checkPrtsAuthno(event) {
	toggleLabel('pauthnoLb','pauthno','out');
	
	var pauthno = document.getElementById("pauthno").value;
	var oMsg = document.getElementById("pauthnoMsg");
	var old_authno = document.getElementById("old_authno").value;

	if (pauthno == "" || document.getElementById("authFlag").value != "Y") {
		oMsg.style.display = "block";
		oMsg.className = "error";
		oMsg.innerHTML = "인증이 필요합니다.";
		return false;
	}
	
	if( old_authno != "" && document.getElementById("authFlag").value == "Y" ) {
		oMsg.style.display = "block";
		oMsg.className = "error gm";
		oMsg.innerHTML = "인증된 상태입니다.";
		return true;
	}

	if (true) {
		oMsg.style.display = "none";
		return true;
	}
	return true;
}

function checkPrtsAgree(event) {
	var oMsg = document.getElementById("pmobnoMsg");
	if (document.getElementById("pterms").checked == false) {
		oMsg.style.display = "block";
		oMsg.className = "error";
		oMsg.innerHTML = "개인정보처리에 동의해주세요.";
		return false;
	}

	if (true) {
		oMsg.style.display = "none";
		return true;
	}
	return true;
}

function viewPrtsAgree() {
	if (document.getElementById("pterms").checked) {
		document.getElementById("ptermsLb").className = "on";
	} else {
		document.getElementById("ptermsLb").className = "";
	}
}

function sendPrtsSmsButton() {

	var res = true;

	if (checkPrtsName('check') != true) {
		res = false;
	}
	if (checkPrtsSex('check') != true) {
		res = false;
	}
	if (checkPrtsBirthday('check') != true) {
		res = false;
	}
	if (checkPrtsMobno('check') != true) {
		res = false;
	}
	if (checkPrtsAgree('check') != true) {
		res = false;
	}
	if (res == false) {
		return false;
	}

	var pnm = document.getElementById("pnm").value;
	var psex = "";
	if (document.getElementById("pman").checked) {
		psex = "M";
	} else {
		psex = "F";
	}

	var s = document.getElementById('pForeign');
	var pForeign = s.options[s.selectedIndex].value;

	var pbirthday = document.getElementById("pbirthday").value;
	var ptelecom = document.getElementById('ptelecom').value;
	var pmobno = document.getElementById("pmobno").value;

	var key = document.getElementById("token_sjoin").value;
	var oMsg = document.getElementById("pmobnoMsg");

	pmobno = pmobno.split("-").join("");
	document.getElementById("pmobno").value = pmobno;

	// 보호자와 쥬니버의 나이차가 18세 미만이면 가입 불가
	var birthday = document.getElementById("birthday").value;
	if (calcAge(pbirthday) - calcAge(birthday) < 19) {
		oMsg.innerHTML = "보호자(법정대리인) 동의 기준이 맞지 않습니다.";
		return false;
	}

	document.getElementById("authFlag").value = "N";

	var url = "/user2/joinAjax.nhn?m=sendPrtsAuthno&psex=" + psex + "&pForeign=" + pForeign + "&pbirthday=" + pbirthday + "&ptelecom=" + ptelecom + "&pmobno="
			+ pmobno + "&key=" + key + "&pnm=" + escape(encodeURIComponent(pnm));

	try {
		var xmlhttp = getXmlHttp();
		xmlhttp.open("GET", url);
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4) {
				var result = xmlhttp.responseText.substr(4, 999);

				oMsg.style.display = "block";
				if (result == "S") {
					oMsg.className = "error gm";
					oMsg.innerHTML = "인증번호를 발송했습니다.<br>인증번호가 오지 않으면 입력하신 정보가 정확한지 확인하여 주세요.";
					return true;
				} else if (result == "Dur") {
					oMsg.className = "error";
					oMsg.innerHTML = "동일한 인증수단은 계속해서 사용할 수 없습니다.";
					return false;
				} else if (result == "Live") {
					oMsg.className = "error";
					oMsg.innerHTML = "해당 번호는 더 이상 가입이 불가능합니다.";
					return false;
				} else if (result == "Dupl") {
					oMsg.className = "error";
					oMsg.innerHTML = "등록 가능한 아이디 수를 초과하였습니다.";
					return false;
				} else if (result == "0011") {
					oMsg.className = "error";
					oMsg.innerHTML = "본인 정보가 일치하지 않습니다.";
					return false;
				} else {
					oMsg.innerHTML = "전화번호를 다시 확인해주세요.";
					return false;
				}
			}
		};
		xmlhttp.send(null);
	} catch (e) {
		if (window.bridgeGotTime) {
			throw e;
		}
	}
	return true;
}

function checkPrtsAuthnoButton() {
	var res = true;

	if (checkPrtsName('check') != true) {
		res = false;
	}
	if (checkPrtsSex('check') != true) {
		res = false;
	}
	if (checkPrtsBirthday('check') != true) {
		res = false;
	}
	if (checkPrtsMobno('check') != true) {
		res = false;
	}
	if (res == false) {
		return false;
	}

	var pnm = document.getElementById("pnm").value;
	var psex = "";
	if (document.getElementById("pman").checked) {
		psex = "M";
	} else {
		psex = "F";
	}
	var pForeign = document.getElementById("pForeign").value;
	var pbirthday = document.getElementById("pbirthday").value;
	var ptelecom = document.getElementById("ptelecom").value;
	var pmobno = document.getElementById("pmobno").value;
	var pauthno = document.getElementById("pauthno").value;

	var key = document.getElementById("token_sjoin").value;
	var oMsg = document.getElementById("pauthnoMsg");

	var url = "/user2/joinAjax.nhn?m=checkPrtsAuthno&psex=" + psex + "&pForeign=" + pForeign + "&pbirthday=" + pbirthday + "&ptelecom=" + ptelecom + "&pmobno="
			+ pmobno + "&pauthno=" + pauthno + "&key=" + key + "&pnm=" + escape(encodeURIComponent(pnm));

	try {
		var xmlhttp = getXmlHttp();
		xmlhttp.open("GET", url);
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4) {
				var result = xmlhttp.responseText.substr(4, 999);

				oMsg.style.display = "block";
				if (result == "S") {
					oMsg.className = "error gm";
					oMsg.innerHTML = "인증이 성공했습니다.";
					document.getElementById("authFlag").value = "Y";
					return true;
				} else if (result == "Cnt") {
					oMsg.className = "error";
					oMsg.innerHTML = "인증을 다시 진행해주세요.";
					return true;
				} else {
					oMsg.className = "error";
					oMsg.innerHTML = "인증번호를 다시 확인해주세요.";
					return false;
				}
			}
		};
		xmlhttp.send(null);
	} catch (e) {
		if (window.bridgeGotTime) {
			throw e;
		}
	}
	return true;
}

function checkIpinAuth(event) {
	var oMsg = document.getElementById("ipinMsg");
	if (document.getElementById("ipinFlag").value != "Y") {
		oMsg.style.display = "block";
		oMsg.className = "error";
		oMsg.innerHTML = "인증이 필요합니다.";
		return false;
	}

	if (true) {
		oMsg.style.display = "none";
		return true;
	}

	return true;
}

function checkIpinAuthM(event) {

	var oMsg = document.getElementById("ipinMsg");
	if (document.getElementById("chk_ipin").checked == false) {
		oMsg.style.display = "block";
		return false;
	}

	if (true) {
		oMsg.style.display = "none";
		return true;
	}

	return true;
}

function winOpenAtCenter(sURL, sWindowName, w, h, sScroll) {
	// 화면 중앙으로 Popup 띄우기.. 스크롤바는 옵션..
	// ex)
	// openWin("test.asp", "winTest", 400, 300); ☞ 스크롤바 없음
	// openWin("test.asp", "winTest", 400, 300, "yes"); ☞ 스크롤바 있음
	// openWin("test.asp", "winTest", 400, 300, "auto"); ☞ 스크롤바 자동

	var x = (screen.width - w) / 2;
	var y = (screen.height - h) / 2;

	if (sScroll == null)
		sScroll = "no";

	var sOption = "";
	sOption = sOption + "toolbar=no, channelmode=no, location=no, directories=no, resizable=no, menubar=no";
	sOption = sOption + ", scrollbars=" + sScroll + ", left=" + x + ", top=" + y + ", width=" + w + ", height=" + h;

	var win = window.open(sURL, sWindowName, sOption);
	return win;
}

function setIpinFlag(val) {
	document.getElementById("ipinFlag").value = val;

	var oMsg = document.getElementById("ipinMsg");
	if (val == "Y") {
		oMsg.style.display = "block";
		oMsg.className = "error gm";
		oMsg.innerHTML = "인증이 성공했습니다.";
		return true;
	} else {
		oMsg.style.display = "block";
		oMsg.className = "error";
		oMsg.innerHTML = "인증이 필요합니다.";
		return false;
	}
	;
}

function popupIpinButton() {
	var ua = window.navigator.userAgent.toLowerCase();
	if (/crios/.test(ua) && /ipad/.test(ua)) {
		alert("접속하신 브라우저에서는 아이핀 인증이 진행되지 않습니다.");
		return false;
	}

	winOpenAtCenter('', 'popup', 448, 500, "auto");
	document.form_ipin.target = "popup";
	document.form_ipin.action = "https://cert.vno.co.kr/ipin.cb";
	document.form_ipin.submit();

	return true;
}

function setIpinNationNo(obj) {
	var s = obj;
	var nationCd = s.options[s.selectedIndex].value;

	document.getElementById("inationMsg").innerHTML = "+" + nationCd;
	document.getElementById("inationNo").value = nationCd;

	return true;
}

function checkIpinMobno(event) {
	toggleLabel('imobnoLb', 'imobno', 'out');

	var imobno = document.getElementById("imobno").value;
	var oMsg = document.getElementById("imobnoMsg");
	var old_imobno = document.getElementById("old_imobno").value;

	if (imobno == "") {
		oMsg.style.display = "block";
		oMsg.className = "error";
		oMsg.innerHTML = "필수 정보입니다.";
		return false;
	}
	
	if( old_imobno != "" && old_imobno != imobno) {
		oMsg.style.display = "block";
		oMsg.innerHTML = "인증을 다시 수행해 주세요.";
		
		document.getElementById("iauthno").value = "";
		document.getElementById("old_imobno").value = "";
		document.getElementById("old_authno").value = "";
		document.getElementById("authFlag").value = "N";		
		
		toggleLabel('iauthnoLb','iauthno','out');
		checkIpinAuthno('check');
		return false;
	}

	if (true) {
		oMsg.style.display = "none";
		return true;
	}

	return true;
}

function checkIpinAuthno(event) {
	toggleLabel('iauthnoLb', 'iauthno', 'out');
	
	var authno = document.getElementById("iauthno").value;
	var oMsg = document.getElementById("iauthnoMsg");
	var old_authno = document.getElementById("old_authno").value;

	if (authno == "" || document.getElementById("authFlag").value != "Y") {
		oMsg.style.display = "block";
		oMsg.className = "error";
		oMsg.innerHTML = "인증이 필요합니다.";
		
		toggleLabel('iauthnoLb', 'iauthno', 'out');
		return false;
	}
	
	if( old_authno != "" && document.getElementById("authFlag").value == "Y" ) {
		oMsg.style.display = "block";
		oMsg.className = "error gm";
		oMsg.innerHTML = "인증된 상태입니다.";
		
		toggleLabel('iauthnoLb', 'iauthno', 'out');
		return true;
	}

	if (true) {
		oMsg.style.display = "none";
		return true;
	}

	return true;
}

function sendIpinSmsButton() {
	var nationNo = document.getElementById("inationNo").value;
	var mobno = document.getElementById("imobno").value;
	var key = document.getElementById("token_sjoin").value;
	var oMsg = document.getElementById("imobnoMsg");

	mobno = mobno.split("-").join("");
	document.getElementById("imobno").value = mobno;

	document.getElementById("authFlag").value = "N";
	try {
		var xmlhttp = getXmlHttp();
		xmlhttp.open("GET", "/user2/joinAjax.nhn?m=sendAuthno&nationNo=" + nationNo + "&mobno=" + mobno + "&key=" + key);
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4) {
				var result = xmlhttp.responseText.substr(4, 999);

				oMsg.style.display = "block";
				if (result == "S") {
					oMsg.className = "error gm";
					oMsg.innerHTML = "인증번호를 발송했습니다.<br>인증번호가 오지 않으면 입력하신 정보가 정확한지 확인하여 주세요.";
					return true;
				} else if (result == "S_ARS") {
					oMsg.className = "error gm";
					oMsg.innerHTML = "전화를 걸고 있습니다.<br>통신사 사정에 따라 연결이 지연될 수 있으니 잠시만 기다려 주세요.";
					return true;				
				} else if (result == "Dur") {
					oMsg.className = "error";
					oMsg.innerHTML = "동일한 인증수단은 계속해서 사용할 수 없습니다.";
					return false;
				} else if (result == "Live") {
					oMsg.className = "error";
					oMsg.innerHTML = "해당 번호는 더 이상 가입이 불가능합니다.";
					return false;
				} else if (result == "Dupl") {
					oMsg.className = "error";
					oMsg.innerHTML = "등록 가능한 아이디 수를 초과하였습니다.";
					return false;
				} else {
					oMsg.innerHTML = "전화번호를 다시 확인해주세요.";
					return false;
				}
			}
		};
		xmlhttp.send(null);
	} catch (e) {
		if (window.bridgeGotTime) {
			throw e;
		}
	}

	return false;
}

function checkIpinAuthnoButton() {
	var authno = document.getElementById("iauthno").value;
	var key = document.getElementById("token_sjoin").value;
	var oMsg = document.getElementById("iauthnoMsg");

	try {
		var xmlhttp = getXmlHttp();
		xmlhttp.open("GET", "/user2/joinAjax.nhn?m=checkAuthno&authno=" + authno + "&key=" + key);
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4) {
				var result = xmlhttp.responseText.substr(4, 999);

				oMsg.style.display = "block";
				if (result == "S") {
					oMsg.className = "error gm";
					oMsg.innerHTML = "인증이 성공했습니다.";
					document.getElementById("authFlag").value = "Y";
					return true;
				} else if (result == "Cnt") {
					oMsg.className = "error";
					oMsg.innerHTML = "인증을 다시 진행해주세요.";
					return true;
				} else {
					oMsg.className = "error";
					oMsg.innerHTML = "인증번호를 다시 확인해주세요.";
					return false;
				}
			}
		};
		xmlhttp.send(null);
	} catch (e) {
		if (window.bridgeGotTime) {
			throw e;
		}
	}

	return true;
}

function checkCaptcha(event) {	
	var autoValue = document.getElementById("autoValue").value;
	var oMsg = document.getElementById("captchaMsg");
	
	if (autoValue == "") {
		oMsg.style.display = "block";
		oMsg.className = "error";
		lua_do('join_kr'+document.getElementById("platform").value,'',ERROR_NO_VALUE_CAPTCHA, document.getElementById("token_sjoin").value, true,'');
		return false;
	}	

	if (true) {
		oMsg.style.display = "none";
		return true;
	}

	return true;
}

function mainSubmit(cnt) {

	var res = true;
	var mode = document.getElementById("platform").value;	
	var agentType = document.getElementById("agentType").value;

	cnt++;
	if (cnt > 10) {
		return false;
	}
	
	if (cnt == 1) {
		if (checkId('join') != true) {
			res = false;
		}
		if (checkPswd1('check') != true) {
			res = false;
		}
		if (checkPswd2('check') != true) {
			res = false;
		}
		if (checkName('check') != true) {
			res = false;
		}
		if(agentType != "iOS_App") {
			if (checkSex('check') != true) {
				res = false;
			}
			if (checkBirthday('check') != true) {
				res = false;
			}			
		}
		
		if (checkEmail('check') != true) {
			res = false;
		}

		var joinMode = document.getElementById("joinMode").value;

		if (joinMode == "unreal") {
			viewUnrealMobileTab();
			if (checkMobno('check') != true) {
				res = false;
			}
			if (checkAuthno('check') != true) {
				res = false;
			}
		}
		if (joinMode == "juniverMobile") {
			viewJuniverMobileTab();
			if (checkPrtsAgree('check') != true) {
				res = false;
			}
			if (checkPrtsName('check') != true) {
				res = false;
			}
			if (checkPrtsSex('check') != true) {
				res = false;
			}
			if (checkPrtsBirthday('check') != true) {
				res = false;
			}
			if (checkPrtsTelecom() != true) {
				res = false;
			}			
			if (checkPrtsMobno('check') != true) {
				res = false;
			}
			if (checkPrtsAuthno('check') != true) {
				res = false;
			}			
		}
		if (joinMode == "juniverIpin") {
			viewJuniverIpinTab();

			if (mode != "M") {
				if (checkIpinAuth('check') != true) {
					res = false;
				}
			} else {
				if (checkIpinAuthM('check') != true) {
					res = false;
				}
			}

			if (checkIpinMobno('check') != true) {
				res = false;
			}
			if (checkIpinAuthno('check') != true) {
				res = false;
			}
		}
	}
	
	var captchaMode = document.getElementById("captchaMode").value;
	if(captchaMode == "Y") {
		if (checkCaptcha('check') != true) {
			res = false;
		}
	}

	var oMsg = document.getElementById("joinMsg");
	if (res == false) {
		oMsg.style.display = "block";
		oMsg.innerHTML = "입력하신 정보를 다시 확인해주세요.";
		lua_do('join_kr'+document.getElementById("platform").value,'',ERROR_JOIN_SUBMIT, document.getElementById("token_sjoin").value, true, document.getElementById("id").value);
		
		if ("undefined" !== typeof log1 && "undefined" !== typeof log2) {
			lua_do2('join_kr'+document.getElementById("platform").value,'',ERROR_JOIN_SUBMIT+";keyboard^"+log2.sb() + ";mouse^"+log1.sb(), document.getElementById("token_sjoin").value, true, document.getElementById("id").value);			
		}
		
		return false;
	}
	if (res == true) {
		if (idFlag != true || pwFlag != true) {
			setTimeout("mainSubmit(" + cnt + ")", 300);
			return false;
		} else 
		{
			oMsg.style.display = "none";
			document.getElementById("pswd1").value = "";
			document.getElementById("pswd2").value = "";

			if (cnt > 1) {
				lua_do('join_kr'+document.getElementById("platform").value,'',SUCCESS_JOIN_SUBMIT, document.getElementById("token_sjoin").value, true, document.getElementById("id").value);
				
				if ("undefined" !== typeof log1 && "undefined" !== typeof log2) {
					lua_do2('join_kr'+document.getElementById("platform").value,'',SUCCESS_JOIN_SUBMIT+";keyboard^"+log2.sb() + ";mouse^"+log1.sb(), document.getElementById("token_sjoin").value, true, document.getElementById("id").value);
				}
				
				document.getElementById('join_form').submit();
			} else {
				return true;
			}
		}
		;
	}
	;

};

function checkShiftDownJoin(e) {
	if (e.which && e.which==16){
		isshift = true;
	}
	
	var oMsg = document.getElementById("pswd1Msg");
	if (e.which && e.which==20){
		if(!isCapslock) {
			isCapslock = true;
			oMsg.style.display = "block";
			oMsg.className = "error";
			oMsg.innerHTML = "Caps Lock이 켜져 있습니다.";
		} else {
			isCapslock = false;
			oMsg.style.display = "none";
		}
	}
	focuswhere=0;

}
